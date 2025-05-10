package co.uniquindio.estructuras.colabedu.Controller;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import co.uniquindio.estructuras.colabedu.Model.Content;

public class ContentCardController {

    // Componentes de texto
    @FXML private Text txt_star;
    @FXML private Text txt_username;
    @FXML private Text txt_date;
    @FXML private Text txt_typeContent;
    @FXML private Text txt_nameContent;
    @FXML private Label txt_description;

    // Componentes multimedia
    @FXML private StackPane mediaContainer;  // Cambiado de VBox a StackPane
    @FXML private ImageView imgPreview;
    @FXML private Label lblFileInfo;
    @FXML private ImageView iconType;

    public void inicializarDatos(Content contenido) {
        // Configurar información básica
        txt_username.setText(contenido.getTheUser().getName());
        txt_date.setText(contenido.getPublicationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        txt_nameContent.setText(contenido.getName());
        txt_typeContent.setText(contenido.getTypeContent());
        txt_description.setText(contenido.getDescription());
        txt_star.setText(String.valueOf(contenido.getTheRating()));

        // Configurar representación visual del contenido
        configurarVisualizacionContenido(contenido);
    }

    private void configurarVisualizacionContenido(Content contenido) {
        // Limpiar contenedor
        mediaContainer.getChildren().clear();
        imgPreview.setVisible(false);
        lblFileInfo.setVisible(false);
        iconType.setVisible(false);

        if (contenido.getFileData() == null || contenido.getFileData().length == 0) {
            return;
        }

        String contentType = contenido.getTypeContent().toLowerCase();

        if (contentType.contains("imagen")) {
            mostrarImagen(contenido);
        } else {
            mostrarIconoTipoArchivo(contenido);
        }
    }

    private void mostrarImagen(Content contenido) {
        try {
            Image image = new Image(new ByteArrayInputStream(contenido.getFileData()));

            // Configurar el ImageView
            imgPreview.setImage(image);
            imgPreview.setPreserveRatio(true);
            imgPreview.setSmooth(true);
            imgPreview.setCache(true);

            // Tamaño fijo para todas las imágenes
            imgPreview.setFitWidth(150);
            imgPreview.setFitHeight(150);

            // Centrar la imagen
            imgPreview.setTranslateX((150 - imgPreview.getFitWidth()) / 2);
            imgPreview.setTranslateY((150 - imgPreview.getFitHeight()) / 2);

            mediaContainer.getChildren().add(imgPreview);
            imgPreview.setVisible(true);

        } catch (Exception e) {
            mostrarIconoTipoArchivo(contenido);
        }
    }

    private void mostrarIconoTipoArchivo(Content contenido) {
        String contentType = contenido.getTypeContent().toLowerCase();
        String iconPath = "/images/";

        if (contentType.contains("video")) {
            iconPath += "video-icon.png";
        } else if (contentType.contains("audio")) {
            iconPath += "audio-icon.png";
        } else if (contentType.contains("documento") || contentType.contains("pdf") || contentType.contains("docx")) {
            iconPath += "doc-icon.png";
        } else {
            iconPath += "file-icon.png";
        }

        try {
            Image icon = new Image(getClass().getResourceAsStream(iconPath));
            iconType.setImage(icon);
            iconType.setVisible(true);
            iconType.setFitWidth(50);
            iconType.setFitHeight(50);

            lblFileInfo.setText(contenido.getFileName());
            lblFileInfo.setVisible(true);

            mediaContainer.getChildren().addAll(iconType, lblFileInfo);
        } catch (Exception e) {
            lblFileInfo.setText("Archivo: " + contenido.getFileName());
            lblFileInfo.setVisible(true);
            mediaContainer.getChildren().add(lblFileInfo);
        }
    }

    @FXML
    void initialize() {
        // Configuración inicial de visibilidad
        imgPreview.setVisible(false);
        lblFileInfo.setVisible(false);
        iconType.setVisible(false);
    }
}