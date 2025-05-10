package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.DAO.UserDAO;
import co.uniquindio.estructuras.colabedu.DAO.UserDAOImpl;
import co.uniquindio.estructuras.colabedu.DB.JDBC;
import co.uniquindio.estructuras.colabedu.DTO.StudentDTO;
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
    private TextField txt_name;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    //Instancias de los servicios
    EmailService emailService = new EmailService();
    //Instancia de la base de datos
    private static final Connection connection = JDBC.getConnection();
    UserDAOImpl userDAO = new UserDAOImpl(connection);


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
        StudentDTO user = new StudentDTO(name, email, username, password);

        //Validar que el nombre de usuario no esté vacío
        if (username.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return;
        }
        //Validar que el nombre del usuario no esté vacío
        if (name.isEmpty()) {
            System.out.println("El nombre del usuario no puede estar vacío.");
            return;
        }
        //Validar que el email del usuario no esté vacío
        if (email.isEmpty()) {
            System.out.println("El email del usuario no puede estar vacío.");
            return;
        }
        //Validar que la contraseña no esté vacía
        if (password.isEmpty()) {
            System.out.println("La contraseña no puede estar vacía.");
            return;
        }
        //Validar que la contraseña y la confirmación coincidan
        if (!password.equals(confirmPassword)) {
            System.out.println("Las contraseñas no coinciden.");
            return;
        }
        else {
            userDAO.save(user);
            emailService.enviarCorreo(email, username); //prueba de correo
            App.setRoot("LogInView", "ColabEdu -Página principal-");
        }
    }

    @FXML
    void initialize() {
        assert txt_confirmPassword != null : "fx:id=\"txt_confirmPassword\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'RegisterView.fxml'.";

    }

}

