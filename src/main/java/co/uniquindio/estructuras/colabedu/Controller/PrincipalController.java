package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.Model.Content;
import co.uniquindio.estructuras.colabedu.Model.Student;
import co.uniquindio.estructuras.colabedu.View.AffinityGraphLauncher;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrincipalController {

    public static List<Content> contenidosTemporales = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_search;

    @FXML
    private ScrollPane content_container;

    @FXML
    private AnchorPane profileSidebar;

    @FXML
    private Pane overlay;

    @FXML
    private VBox contenedorContenidos;

    private void toggleProfileSidebar(boolean show) {
        if (show) {
            profileSidebar.setVisible(true);
            overlay.setVisible(true);
        }

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), profileSidebar);
        tt.setFromX(show ? 300 : 0);
        tt.setToX(show ? 0 : 300);
        tt.setOnFinished(e -> {
            if (!show) {
                profileSidebar.setVisible(false);
                overlay.setVisible(false);
            }
        });
        tt.play();
    }

    @FXML
    void btn_perfil(MouseEvent event) {
        toggleProfileSidebar(true);
    }

    @FXML
    void handleConfiguracion(ActionEvent event) throws IOException {
        App.setRoot("ProfileSettingsView", "ColabEdu - Información de tu perfil");
    }

    @FXML
    void handlePrivacidad(ActionEvent event) {
        System.out.println("Abriendo configuración de privacidad...");
    }

    @FXML
    void handleCerrarSesion(ActionEvent event) throws IOException {
        App.setRoot("LogInView", "ColabEdu - Inicio de sesión");
    }

    @FXML
    void closeProfileSidebar(ActionEvent event) {
        toggleProfileSidebar(false);
    }

    @FXML
    public void refrescarContenidos() {
        contenedorContenidos.getChildren().clear();

        try {
            for (Content contenido : contenidosTemporales) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                AnchorPane card = loader.load();

                ContentCardController cardController = loader.getController();
                cardController.inicializarDatos(contenido);

                card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                VBox.setMargin(card, new Insets(0, 0, 15, 0));

                contenedorContenidos.getChildren().add(card);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las tarjetas de contenido: " + e.getMessage());
        }
    }

    @FXML
    void btn_colabEdu(MouseEvent event) {
        System.out.println("Botón ColabEdu");
    }

    @FXML
    void btn_contenidos(MouseEvent event) throws IOException {
        App.setRoot("ContentsView", "ColabEdu - Tus contenidos subidos");
    }

    @FXML
    void btn_crear(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/LoadContentView.fxml"));
            Parent root = loader.load();

            LoadContentController loadController = loader.getController();
            loadController.setPrincipalController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Content> getContenidosTemporales() {
        return contenidosTemporales;
    }

    @FXML
    void btn_grupos(MouseEvent event) {
        System.out.println("Botón Grupos");

        // Crear datos de prueba
        Student estudiantePrueba = new Student("Juan Pablo", "juanp@gmail.com", "001", "pass1234", "Universidad del Quindío");
        Student amigo1 = new Student("María García", "maria@gmail.com", "002", "pass1235", "Universidad del Quindío");
        Student amigo2 = new Student("Carlos Barahona", "carlos@gmail.com", "003", "pass1236", "Universidad del Quindío");
        Student amigo3 = new Student("Willinton Vergara", "carlos@gmail.com", "003", "pass1236", "Universidad del Quindío");
        Student amigo4 = new Student("Elkin Bermu", "carlos@gmail.com", "003", "pass1236", "Universidad del Quindío");
        Student amigo5 = new Student("Esteban Maya", "carlos@gmail.com", "003", "pass1236", "Universidad del Quindío");

        // Establecer amistades
        estudiantePrueba.addFriend(amigo1);
        estudiantePrueba.addFriend(amigo2);
        estudiantePrueba.addFriend(amigo3);
        estudiantePrueba.addFriend(amigo4);
        estudiantePrueba.addFriend(amigo5);

        // Mostrar grafo
        AffinityGraphLauncher.showAffinityGraph(estudiantePrueba);
    }

    // Método de ejemplo para obtener el estudiante actual, aquí ignacio, debe ir el currentUser
    private Student obtenerEstudianteActual() {
        // Implementa la lógica para obtener el estudiante actual
        return new Student("Juan Pablo", "juan@gmail.com", "001", "pass123", "Universidad del Quindío");
    }

    @FXML
    void btn_principal(MouseEvent event) {
        System.out.println("Botón Principal");
    }

    @FXML
    void btn_soliAyuda(MouseEvent event) {
        System.out.println("Botón Solicitar ayuda");
    }

    @FXML
    void btn_solicitudesAyuda(MouseEvent event) {
        System.out.println("Botón Solicitudes ayuda");
    }

    @FXML
    void btn_valoraciones(MouseEvent event) {
        System.out.println("Botón valoraciones");
    }

    @FXML
    void initialize() {
        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'PrincipalView.fxml'.";
        assert contenedorContenidos != null : "fx:id=\"contenedorContenidos\" was not injected: check your FXML file 'PrincipalView.fxml'.";

        profileSidebar.setTranslateX(300);
    }
}