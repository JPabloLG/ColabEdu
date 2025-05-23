package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.Util.AlertService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class ProfileSettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_confirmPassword;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_newPassword;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("PrincipalView", "ColabEdu -Página principal-");
    }

    @FXML
    void btn_confirm(MouseEvent event) throws IOException {
        String password = txt_password.getText();
        String newPassword = txt_newPassword.getText();
        String confirmPassword = txt_confirmPassword.getText();

        if (verifyPassword()){
            //aqui va todo lo de el cambio en la BD como tal
            AlertService.showInfo("Los datos se cambiaron correctamente");
            App.setRoot("PrincipalView", "ColabEdu -Página principal-");
        }
    }

    private boolean verifyPassword() {
        String password = txt_password.getText();
        String newPassword = txt_newPassword.getText();
        String confirmPassword = txt_confirmPassword.getText();

        /*
        if (password == currentUser.getPassword()) {

        }else{
            AlertService.showError("La contraseña actual está mal digitada");
            return false;
        }
        */

        //esto va adentro del if de arriba
        if (newPassword.equals(confirmPassword)) {
            return true;
        }else {
            AlertService.showError("Las contraseñas no coinciden");
            return false;
        }
    }

    @FXML
    void initialize() {
        //txt_email.setText(currentUser.getEmail());
        //txt_username.setText(currentUser.getUsername());
        //txt_name.setText(currentUser.getName());



        assert txt_confirmPassword != null : "fx:id=\"txt_confirmPassword\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_newPassword != null : "fx:id=\"txt_newPassword\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
    }
}
