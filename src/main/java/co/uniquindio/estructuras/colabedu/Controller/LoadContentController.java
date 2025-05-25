package co.uniquindio.estructuras.colabedu.Controller;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
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

        byte[] datos = esTexto ?
                txt_description.getText().getBytes("UTF-8") :
                Files.readAllBytes(selectedFile.toPath());

        String nombreArchivo = esTexto ?
                "texto_" + System.currentTimeMillis() + ".txt" :
                selectedFile.getName();

        String tipoArchivo = esTexto ?
                "text/plain" :
                Files.probeContentType(selectedFile.toPath());

        User autor = new Student(
                "usuarioActual",
                "usuario@ejemplo.com",
                "user123",
                "pass123",
                "Universidad del Quindío"
        );
        return new Content(
                txt_title.getText(),
                LocalDateTime.now(),
                tipo,
                txt_description.getText(),
                txt_topic.getText(),
                autor,
                new Rating(), // Rating inicial vacío
                datos,
                nombreArchivo,
                tipoArchivo
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
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
