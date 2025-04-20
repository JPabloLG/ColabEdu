package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.Util.EmailService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_confirmPassword;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_grade;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_school;

    @FXML
    private TextField txt_username;

    EmailService emailService = new EmailService();

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("LogInView", "ColabEdu -Inicia sesión-");
    }

    @FXML
    void btn_signIn(MouseEvent event) throws IOException {
        String email = txt_email.getText();
        String name = txt_name.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String confirmPassword = txt_confirmPassword.getText();

        emailService.enviarCorreo(email, username); //prueba de correo
    }

    @FXML
    void initialize() {
        assert txt_confirmPassword != null : "fx:id=\"txt_confirmPassword\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_grade != null : "fx:id=\"txt_grade\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_school != null : "fx:id=\"txt_school\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'RegisterView.fxml'.";

    }

}
