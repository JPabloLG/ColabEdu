package co.uniquindio.estructuras.colabedu.Controller;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import co.uniquindio.estructuras.colabedu.Model.*;
import co.uniquindio.estructuras.colabedu.Util.AlertService;

public class LoadContentController {

    @FXML private TextField txt_title;
    @FXML private TextField txt_topic;
    @FXML private ComboBox<String> cb_typeContent;
    @FXML private TextArea txt_description;
    @FXML private Label lbl_fileName;
    @FXML private Button btn_chooseFile;

    private PrincipalController principalController;
    private File selectedFile;

    @FXML
    void initialize() {
        // Inicializar ComboBox
        cb_typeContent.getItems().setAll("Imagen", "Audio", "Video", "Texto");
        cb_typeContent.getSelectionModel().selectFirst();

        // Listener para cambiar el comportamiento según el tipo seleccionado
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
        if (txt_title.getText().isEmpty()) {
            AlertService.showError("El título es obligatorio");
            return;
        }

        try {
            Content nuevoContenido;
            String tipoSeleccionado = cb_typeContent.getValue();

            if ("Texto".equals(tipoSeleccionado)) {
                // Crear contenido de texto directo
                if (txt_description.getText().isEmpty()) {
                    AlertService.showError("La descripción es obligatoria para contenido de texto");
                    return;
                }

                nuevoContenido = createContent(
                        txt_title.getText(),
                        "Texto",
                        txt_description.getText(),
                        txt_topic.getText(),
                        txt_description.getText().getBytes("UTF-8"),
                        "texto_directo.txt",
                        "text/plain"
                );
            } else {
                // Validar archivo para otros tipos de contenido
                if (selectedFile == null) {
                    AlertService.showError("Debe seleccionar un archivo para el tipo: " + tipoSeleccionado);
                    return;
                }

                byte[] fileData = Files.readAllBytes(selectedFile.toPath());
                String fileType = Files.probeContentType(selectedFile.toPath());

                nuevoContenido = createContent(
                        txt_title.getText(),
                        tipoSeleccionado,
                        txt_description.getText(),
                        txt_topic.getText(),
                        fileData,
                        selectedFile.getName(),
                        fileType
                );
            }

            if (principalController != null) {
                principalController.getContenidosTemporales().add(nuevoContenido);
                principalController.refrescarContenidos();
            }

            Stage stage = (Stage) lbl_fileName.getScene().getWindow();
            stage.close();

            AlertService.showInfo("Contenido subido correctamente");
        } catch (Exception e) {
            AlertService.showError("Error al subir contenido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Content createContent(String title, String type, String description,
                                  String topic, byte[] fileData, String fileName,
                                  String fileType) {
        User author = new Student(
                "defaultUser",
                "user@example.com",
                "user123",
                "password123",
                "Universidad del Quindío"
        );

        Rating rating = new Rating();

        return new Content(
                title,
                LocalDateTime.now(),
                type,
                description,
                topic,
                author,
                rating,
                fileData,
                fileName,
                fileType != null ? fileType : "application/octet-stream"
        );
    }

    @FXML
    void btn_back() {
        Stage stage = (Stage) lbl_fileName.getScene().getWindow();
        stage.close();
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }
}