package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.Model.Content;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

public class ContentsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox contenedorContenidos;

    @FXML
    private ScrollPane content_container;

    @FXML
    private TextField txt_search;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarContenidos();
    }

    private void cargarContenidos() {
        contenedorContenidos.getChildren().clear();

        // Obtener los contenidos del PrincipalController
        for (Content contenido : PrincipalController.contenidosTemporales) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/contentCard.fxml"));
                AnchorPane card = loader.load();

                ContentCardController cardController = loader.getController();
                cardController.inicializarDatos(contenido);

                card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                VBox.setMargin(card, new Insets(0, 0, 15, 0));

                contenedorContenidos.getChildren().add(card);

            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta de contenido: " + e.getMessage());
            }
        }
    }

    @FXML
    void btn_colabEdu(MouseEvent event) throws IOException {
        App.setRoot("PrincipalView", "ColabEdu - Página principal");
    }

    @FXML
    void initialize() {
        assert contenedorContenidos != null : "fx:id=\"contenedorContenidos\" was not injected: check your FXML file 'ContentsView.fxml'.";
        assert content_container != null : "fx:id=\"content_container\" was not injected: check your FXML file 'ContentsView.fxml'.";
        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'ContentsView.fxml'.";
    }
}