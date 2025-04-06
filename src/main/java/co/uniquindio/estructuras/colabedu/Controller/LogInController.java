package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void btn_createAccount(MouseEvent event) throws IOException {
        App.setRoot("RegisterView", "ColabEdu -Resgitro de Usuario-");
    }

    @FXML
    void btn_logIn(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'LogInView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'LogInView.fxml'.";

    }
}
