package co.uniquindio.estructuras.colabedu.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import co.uniquindio.estructuras.colabedu.Model.*;
import co.uniquindio.estructuras.colabedu.Util.AlertService;
import co.uniquindio.estructuras.colabedu.DAO.ContentDAO;
import co.uniquindio.estructuras.colabedu.DAO.ContentDAOImpl;
import co.uniquindio.estructuras.colabedu.DAO.ContentDTO;

public class LoadContentController {

    private static final User currentUser = AcademicSocialNetwork.getSingleton().getCurrentUser();
    @FXML private TextField txt_title;
    @FXML private TextField txt_topic;
    @FXML private ComboBox<String> cb_typeContent;
    @FXML private TextArea txt_description;
    @FXML private Label lbl_fileName;
    @FXML private Button btn_chooseFile;

    private PrincipalController principalController;
    private File selectedFile;
    private ContentDAO contentDAO = new ContentDAOImpl();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    void initialize() {
        cb_typeContent.getItems().setAll("Imagen", "Audio", "Video", "Texto");
        cb_typeContent.getSelectionModel().selectFirst();

        cb_typeContent.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean esTexto = "Texto".equals(newVal);
            btn_chooseFile.setDisable(esTexto);
            lbl_fileName.setText(esTexto ? "Preparado para texto directo" : "Ningún archivo seleccionado");
            if (esTexto) selectedFile = null;
        });
    }

    @FXML
    void btn_chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"),
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio", "*.mp3", "*.wav", "*.ogg"),
                new FileChooser.ExtensionFilter("Video", "*.mp4", "*.avi", "*.mov")
        );

        Stage stage = (Stage) lbl_fileName.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            lbl_fileName.setText("Archivo seleccionado: " + selectedFile.getName());
            autoDetectarTipoArchivo();
        }
    }

    private void autoDetectarTipoArchivo() {
        String fileName = selectedFile.getName().toLowerCase();
        if (fileName.matches(".*\\.(png|jpg|jpeg|gif)$")) {
            cb_typeContent.setValue("Imagen");
        } else if (fileName.matches(".*\\.(mp3|wav|ogg)$")) {
            cb_typeContent.setValue("Audio");
        } else if (fileName.matches(".*\\.(mp4|avi|mov)$")) {
            cb_typeContent.setValue("Video");
        }
    }

    @FXML
    void btn_loadContent() {
        if (validarCampos()) {
            try {
                Content nuevoContenido = crearContenido();

                if (principalController != null) {
                    principalController.getContenidosTemporales().add(nuevoContenido);
                    principalController.refrescarContenidos();
                }

                Stage stage = (Stage) lbl_fileName.getScene().getWindow();
                stage.close();
                shutdown();

                AlertService.showInfo("Contenido subido correctamente");
            } catch (Exception e) {
                AlertService.showError("Error al subir contenido: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean validarCampos() {
        if (txt_title.getText().trim().isEmpty()) {
            AlertService.showError("El título es requerido");
            return false;
        }

        if (!"Texto".equals(cb_typeContent.getValue()) && selectedFile == null) {
            AlertService.showError("Seleccione un archivo");
            return false;
        }

        return true;
    }

    private Content crearContenido() throws IOException {
        String tipo = cb_typeContent.getValue();
        boolean esTexto = "Texto".equals(tipo);
        LocalDateTime fechaPublicacion = LocalDateTime.now();

        // Crear directorio para archivos si no existe
        Path directorioArchivos = Paths.get("files");
        if (!Files.exists(directorioArchivos)) {
            Files.createDirectories(directorioArchivos);
        }

        String nombreArchivo;
        String rutaArchivo;
        String tipoArchivo;
        byte[] datos;

        if (esTexto) {
            // Para contenido de tipo texto
            nombreArchivo = "texto_" + System.currentTimeMillis() + ".txt";
            rutaArchivo = directorioArchivos.resolve(nombreArchivo).toString();
            tipoArchivo = "text/plain";
            datos = txt_description.getText().getBytes("UTF-8");

            // Guardar el texto en un archivo
            try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(datos);
            }
        } else {
            // Para contenido con archivo
            nombreArchivo = selectedFile.getName();
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
            String nombreUnico = System.currentTimeMillis() + extension;
            rutaArchivo = directorioArchivos.resolve(nombreUnico).toString();
            tipoArchivo = Files.probeContentType(selectedFile.toPath());
            datos = Files.readAllBytes(selectedFile.toPath());

            // Copiar el archivo a la carpeta de archivos
            Files.copy(selectedFile.toPath(), Paths.get(rutaArchivo), StandardCopyOption.REPLACE_EXISTING);
        }

        // Crear el objeto de contenido
        User autor = AcademicSocialNetwork.getSingleton().getCurrentUser();

        Content nuevoContenido = new Content(
                txt_title.getText(),
                fechaPublicacion,
                tipo,
                txt_description.getText(),
                txt_topic.getText(),
                autor,
                new Rating(), // Rating inicial vacío
                datos,
                nombreArchivo,
                tipoArchivo
        );

        // Crear un hilo para guardar el contenido en la base de datos
        final String finalRutaArchivo = rutaArchivo;
        executorService.submit(() -> {
            try {
                // Crear DTO para guardar en la base de datos
                ContentDTO contentDTO = new ContentDTO(
                        0, // ID será generado por la base de datos
                        nuevoContenido.getName(),
                        nuevoContenido.getDescription(),
                        nuevoContenido.getTypeContent(),
                        finalRutaArchivo, // Ruta del archivo
                        Integer.parseInt(currentUser.getId()),
                        nuevoContenido.getPublicationDate(),
                        nuevoContenido.getSubject()
                );

                // Guardar en la base de datos
                contentDAO.save(contentDTO);

                System.out.println("Contenido guardado en la base de datos con ID: " + contentDTO.getId());
            } catch (Exception e) {
                System.err.println("Error al guardar contenido en la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        });

        return nuevoContenido;
    }

    @FXML
    void btn_back() {
        shutdown();
        Stage stage = (Stage) lbl_fileName.getScene().getWindow();
        stage.close();
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    /**
     * Método para cerrar el ExecutorService cuando el controlador ya no se necesita.
     * Debe ser llamado cuando se cierra la ventana o se destruye el controlador.
     */
    public void shutdown() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
