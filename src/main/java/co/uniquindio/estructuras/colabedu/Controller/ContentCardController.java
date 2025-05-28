package co.uniquindio.estructuras.colabedu.Controller;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import co.uniquindio.estructuras.colabedu.Model.Content;

public class ContentCardController {

    @FXML private Text txt_rating;
    //Text Components
    @FXML private Text txt_star;
    @FXML private Text txt_username;
    @FXML private Text txt_date;
    @FXML private Text txt_typeContent;
    @FXML private Text txt_nameContent;
    @FXML private Label txt_description;
    @FXML private StackPane mediaContainer;
    @FXML private ImageView imgPreview;
    @FXML private Label lblFileInfo;
    @FXML private ImageView iconType;

    public void inicializarDatos(Content contenido) {
        //Set the basic information
        txt_username.setText(contenido.getTheUser().getName());
        txt_date.setText(contenido.getPublicationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        txt_nameContent.setText(contenido.getName());
        txt_typeContent.setText(contenido.getTypeContent());
        txt_description.setText(contenido.getDescription());
        txt_rating.setText(String.valueOf(calcularRatingPromedio(contenido)));

        // Configurar representación visual del contenido
        configurarVisualizacionContenido(contenido);
    }

    private double calcularRatingPromedio(Content contenido) {
        if (contenido.getTheRating().isEmpty()) {
            return 0.0;
        }
        return contenido.getTheRating().stream()
                .mapToDouble(r -> r.getAverageRating())
                .average()
                .orElse(0.0);
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
        String fileExtension = contenido.getFileExtension().toLowerCase();

        if (contentType.contains("imagen") ||
                fileExtension.equals("jpg") ||
                fileExtension.equals("jpeg") ||
                fileExtension.equals("png") ||
                fileExtension.equals("gif")) {
            mostrarImagen(contenido);
        } else if (contentType.contains("video") ||
                fileExtension.equals("mp4") ||
                fileExtension.equals("avi") ||
                fileExtension.equals("mov") ||
                fileExtension.equals("wmv")) {
            mostrarVideo(contenido);
        } else if (contentType.contains("audio") ||
                fileExtension.equals("mp3") ||
                fileExtension.equals("wav") ||
                fileExtension.equals("ogg")) {
            mostrarAudio(contenido);
        } else {
            mostrarIconoTipoArchivo(contenido);
        }
    }

    private void mostrarImagen(Content contenido) {
        try {
            Image image;

            // Primero intentamos cargar la imagen desde fileData si está disponible
            if (contenido.getFileData() != null && contenido.getFileData().length > 0) {
                image = new Image(new ByteArrayInputStream(contenido.getFileData()));
            }
            // Si no hay datos en fileData, intentamos cargar desde el archivo en la carpeta "files"
            else if (contenido.getFileName() != null && !contenido.getFileName().isEmpty()) {
                File imageFile = new File("files/" + contenido.getFileName());
                if (imageFile.exists()) {
                    image = new Image(imageFile.toURI().toString());
                } else {
                    throw new IOException("Archivo de imagen no encontrado: " + imageFile.getAbsolutePath());
                }
            } else {
                throw new IOException("No hay datos de imagen disponibles");
            }

            imgPreview.setImage(image);
            imgPreview.setPreserveRatio(true);
            imgPreview.setSmooth(true);
            imgPreview.setCache(true);
            imgPreview.setFitWidth(150);
            imgPreview.setFitHeight(150);
            mediaContainer.getChildren().add(imgPreview);
            imgPreview.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error al mostrar imagen: " + e.getMessage());
            mostrarIconoTipoArchivo(contenido);
        }
    }

    private void mostrarVideo(Content contenido) {
        try {
            // Mostrar icono de video
            String iconPath = "/images/video-icon.png";
            try {
                Image icon = new Image(getClass().getResourceAsStream(iconPath));
                iconType.setImage(icon);
                iconType.setVisible(true);
                iconType.setFitWidth(50);
                iconType.setFitHeight(50);
            } catch (Exception e) {
                // Si no se puede cargar el icono, continuar sin él
            }

            // Crear botón para reproducir video
            Button btnPlay = new Button("Reproducir Video");
            btnPlay.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

            // Crear contenedor vertical para organizar elementos
            VBox mediaBox = new VBox(5);

            // Mostrar información del archivo
            lblFileInfo.setText(contenido.getFileName());
            lblFileInfo.setVisible(true);

            // Agregar elementos al contenedor
            if (iconType.getImage() != null) {
                mediaBox.getChildren().addAll(iconType, lblFileInfo, btnPlay);
            } else {
                mediaBox.getChildren().addAll(lblFileInfo, btnPlay);
            }

            // Configurar acción del botón
            btnPlay.setOnAction(event -> {
                try {
                    File videoFile;

                    // Primero intentamos usar fileData si está disponible
                    if (contenido.getFileData() != null && contenido.getFileData().length > 0) {
                        // Guardar el archivo en una ubicación temporal
                        videoFile = File.createTempFile("video_", "." + contenido.getFileExtension());
                        videoFile.deleteOnExit(); // El archivo se eliminará al cerrar la aplicación

                        try (FileOutputStream fos = new FileOutputStream(videoFile)) {
                            fos.write(contenido.getFileData());
                        }
                    }
                    // Si no hay datos en fileData, intentamos cargar desde el archivo en la carpeta "files"
                    else if (contenido.getFileName() != null && !contenido.getFileName().isEmpty()) {
                        videoFile = new File("files/" + contenido.getFileName());
                        if (!videoFile.exists()) {
                            throw new IOException("Archivo de video no encontrado: " + videoFile.getAbsolutePath());
                        }
                    } else {
                        throw new IOException("No hay datos de video disponibles");
                    }

                    // Abrir el archivo con la aplicación predeterminada del sistema
                    Desktop.getDesktop().open(videoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    lblFileInfo.setText("Error al reproducir: " + e.getMessage());
                }
            });

            mediaContainer.getChildren().add(mediaBox);
        } catch (Exception e) {
            System.err.println("Error al mostrar video: " + e.getMessage());
            mostrarIconoTipoArchivo(contenido);
        }
    }

    private void mostrarAudio(Content contenido) {
        try {
            // Mostrar icono de audio
            String iconPath = "/images/audio-icon.png";
            try {
                Image icon = new Image(getClass().getResourceAsStream(iconPath));
                iconType.setImage(icon);
                iconType.setVisible(true);
                iconType.setFitWidth(50);
                iconType.setFitHeight(50);
            } catch (Exception e) {
                // Si no se puede cargar el icono, continuar sin él
            }

            // Crear botón para reproducir audio
            Button btnPlay = new Button("Reproducir Audio");
            btnPlay.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

            // Crear contenedor vertical para organizar elementos
            VBox mediaBox = new VBox(5);

            // Mostrar información del archivo
            lblFileInfo.setText(contenido.getFileName());
            lblFileInfo.setVisible(true);

            // Agregar elementos al contenedor
            if (iconType.getImage() != null) {
                mediaBox.getChildren().addAll(iconType, lblFileInfo, btnPlay);
            } else {
                mediaBox.getChildren().addAll(lblFileInfo, btnPlay);
            }

            // Configurar acción del botón
            btnPlay.setOnAction(event -> {
                try {
                    File audioFile;

                    // Primero intentamos usar fileData si está disponible
                    if (contenido.getFileData() != null && contenido.getFileData().length > 0) {
                        // Guardar el archivo en una ubicación temporal
                        audioFile = File.createTempFile("audio_", "." + contenido.getFileExtension());
                        audioFile.deleteOnExit(); // El archivo se eliminará al cerrar la aplicación

                        try (FileOutputStream fos = new FileOutputStream(audioFile)) {
                            fos.write(contenido.getFileData());
                        }
                    }
                    // Si no hay datos en fileData, intentamos cargar desde el archivo en la carpeta "files"
                    else if (contenido.getFileName() != null && !contenido.getFileName().isEmpty()) {
                        audioFile = new File("files/" + contenido.getFileName());
                        if (!audioFile.exists()) {
                            throw new IOException("Archivo de audio no encontrado: " + audioFile.getAbsolutePath());
                        }
                    } else {
                        throw new IOException("No hay datos de audio disponibles");
                    }

                    // Abrir el archivo con la aplicación predeterminada del sistema
                    Desktop.getDesktop().open(audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    lblFileInfo.setText("Error al reproducir: " + e.getMessage());
                }
            });

            mediaContainer.getChildren().add(mediaBox);
        } catch (Exception e) {
            System.err.println("Error al mostrar audio: " + e.getMessage());
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
        } else if (contentType.contains("texto")) {
            iconPath += "text-icon.png";
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