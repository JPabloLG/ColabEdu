package co.uniquindio.estructuras.colabedu.Controller;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadContentController {

    // Componentes FXML - Asegúrate que los nombres coincidan EXACTAMENTE
    @FXML private TextField txt_title;
    @FXML private TextField txt_topic;
    @FXML private TextField txt_typeContent;
    @FXML private TextArea txt_description;  // Cambiado de TextField a TextArea
    @FXML private Label lbl_fileName;        // Asegúrate que existe en el FXML

    private PrincipalController principalController;
    private File selectedFile;

    @FXML
    void initialize() {
        // Validación de inyección de dependencias
        if (txt_title == null) throw new AssertionError("txt_title no fue inyectado");
        if (txt_topic == null) throw new AssertionError("txt_topic no fue inyectado");
        if (txt_typeContent == null) throw new AssertionError("txt_typeContent no fue inyectado");
        if (txt_description == null) throw new AssertionError("txt_description no fue inyectado");
        if (lbl_fileName == null) throw new AssertionError("lbl_fileName no fue inyectado");
    }

    @FXML
    void btn_chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        // Configurar filtros
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"),
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio", "*.mp3", "*.wav", "*.ogg"),
                new FileChooser.ExtensionFilter("Video", "*.mp4", "*.avi", "*.mov"),
                new FileChooser.ExtensionFilter("Documentos", "*.pdf", "*.docx", "*.txt")
        );

        Stage stage = (Stage) lbl_fileName.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            lbl_fileName.setText("Archivo seleccionado: " + selectedFile.getName());

            // Auto-detectar tipo de contenido si está vacío
            if (txt_typeContent.getText().isEmpty()) {
                String fileName = selectedFile.getName().toLowerCase();
                if (fileName.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                    txt_typeContent.setText("Imagen");
                } else if (fileName.matches(".*\\.(mp3|wav|ogg)$")) {
                    txt_typeContent.setText("Audio");
                } else if (fileName.matches(".*\\.(mp4|avi|mov)$")) {
                    txt_typeContent.setText("Video");
                } else {
                    txt_typeContent.setText("Documento");
                }
            }
        }
    }

    @FXML
    void btn_loadContent() {
        // Validaciones básicas reforzadas
        if (txt_title == null || txt_title.getText().isEmpty()) {
            showAlert("Error", "El título es obligatorio");
            return;
        }

        if (selectedFile == null || !selectedFile.exists()) {
            showAlert("Error", "Debe seleccionar un archivo válido");
            return;
        }

        try {
            // Leer archivo con verificación
            if (!selectedFile.canRead()) {
                showAlert("Error", "No se puede leer el archivo seleccionado");
                return;
            }

            byte[] fileData = Files.readAllBytes(selectedFile.toPath());
            String fileType = Files.probeContentType(selectedFile.toPath());

            // Valores por defecto seguros
            String title = txt_title.getText();
            String type = txt_typeContent.getText() != null ? txt_typeContent.getText() : "Sin tipo";
            String description = txt_description.getText() != null ? txt_description.getText() : "";
            String topic = txt_topic.getText() != null ? txt_topic.getText() : "General";

            // Crear contenido de manera segura
            Content nuevoContenido = createContent(
                    title,
                    type,
                    description,
                    topic,
                    fileData,
                    selectedFile.getName(),
                    fileType
            );

            // Añadir a la lista principal
            if (principalController != null) {
                principalController.getContenidosTemporales().add(nuevoContenido);
                principalController.refrescarContenidos(); // Esta línea es crítica
            }

            // Cerrar ventana
            Stage stage = (Stage) lbl_fileName.getScene().getWindow();
            stage.close();

            showAlert("Éxito", "Contenido subido correctamente");

        } catch (Exception e) {
            showAlert("Error", "Error al subir contenido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Content createContent(String title, String type, String description,
                                  String topic, byte[] fileData, String fileName,
                                  String fileType) {
        // Usuario temporal - reemplaza con tu lógica real
        User author = new Moderator(
                "defaultUser",
                "user@example.com",
                "user123",
                "password123"
        );

        // Rating inicial
        Rating rating = new Rating(0);

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}