package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.Model.Content;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    @FXML private VBox contenedorContenidos;

    public void refrescarContenidos() {
        contenedorContenidos.getChildren().clear();
        for (Content contenido : contenidosTemporales) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                AnchorPane card = loader.load();
                ContentCardController controller = loader.getController();
                controller.inicializarDatos(contenido);
                contenedorContenidos.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btn_colabEdu(MouseEvent event) {
        System.out.println("Botón ColabEdu");
    }

    @FXML
    void btn_contenidos(MouseEvent event) {
        System.out.println("Botón Contenidos");
    }

    @FXML
    void btn_crear(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/LoadContentView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la ventana de carga
            LoadContentController loadController = loader.getController();
            loadController.setPrincipalController(this); // Pasar la referencia

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter para la lista de contenidos
    public List<Content> getContenidosTemporales() {
        return contenidosTemporales;
    }

    @FXML
    void btn_grupos(MouseEvent event) {
        System.out.println("Botón Grupos");

    }

    @FXML
    void btn_perfil(MouseEvent event) {
        System.out.println("Botón Perfil");
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

    }

}
