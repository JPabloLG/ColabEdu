package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.Model.Content;
import co.uniquindio.estructuras.colabedu.Model.Moderator;
import co.uniquindio.estructuras.colabedu.Model.Rating;
import co.uniquindio.estructuras.colabedu.Model.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoadContentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_topic;

    @FXML
    private TextField txt_typeContent;

    private PrincipalController principalController;

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Método para establecer la referencia
    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    @FXML
    void btn_loadContent(MouseEvent event) throws IOException {
        // Obtener datos del formulario
        String titulo = txt_title.getText();
        String tema = txt_topic.getText();
        String tipo = txt_typeContent.getText();
        String descripcion = txt_description.getText();

        // Crear contenido temporal (simular usuario y rating)
        Content nuevoContenido = new Content(
                titulo,
                LocalDateTime.now(),
                tipo,
                descripcion,
                tema,
                new Moderator("JPabloLG", "juangmail.com", "00987", "123"), // Usuario temporal
                new Rating(0) // Rating inicial
        );
        principalController.getContenidosTemporales().add(nuevoContenido); // Añade un getter en PrincipalController

        // Cerrar ventana
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Actualizar la vista principal
        principalController.refrescarContenidos();
    }

    @FXML
    void initialize() {
        assert txt_description != null : "fx:id=\"txt_description\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_title != null : "fx:id=\"txt_title\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_topic != null : "fx:id=\"txt_topic\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_typeContent != null : "fx:id=\"txt_typeContent\" was not injected: check your FXML file 'LoadContentView.fxml'.";

    }

}
