package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
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

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void btn_loadContent(MouseEvent event) throws IOException {
        App.setRoot("PrincipalView", "ColabEdu -Página principal-");
    }

    @FXML
    void initialize() {
        assert txt_description != null : "fx:id=\"txt_description\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_title != null : "fx:id=\"txt_title\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_topic != null : "fx:id=\"txt_topic\" was not injected: check your FXML file 'LoadContentView.fxml'.";
        assert txt_typeContent != null : "fx:id=\"txt_typeContent\" was not injected: check your FXML file 'LoadContentView.fxml'.";

    }

}
