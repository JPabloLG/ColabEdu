package co.uniquindio.estructuras.colabedu.Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.Model.Content;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ContentCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text txt_date;

    @FXML
    private Label txt_description;

    @FXML
    private Text txt_star;

    @FXML
    private Text txt_typeContent;

    @FXML
    private Text txt_username;

    public void inicializarDatos(Content contenido) {
        txt_username.setText(contenido.getTheUser().getName());
        txt_date.setText(contenido.getPublicationDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        txt_typeContent.setText(contenido.getTypeContent());
        txt_description.setText(contenido.getDescription());
        txt_star.setText(String.valueOf(contenido.getTheRating().getRating()));
    }

    @FXML
    void initialize() {
        assert txt_date != null : "fx:id=\"txt_date\" was not injected: check your FXML file 'contentCard.fxml'.";
        assert txt_description != null : "fx:id=\"txt_description\" was not injected: check your FXML file 'contentCard.fxml'.";
        assert txt_star != null : "fx:id=\"txt_star\" was not injected: check your FXML file 'contentCard.fxml'.";
        assert txt_typeContent != null : "fx:id=\"txt_typeContent\" was not injected: check your FXML file 'contentCard.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'contentCard.fxml'.";

    }

}
