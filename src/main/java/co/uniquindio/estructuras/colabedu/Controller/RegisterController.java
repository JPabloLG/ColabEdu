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
    private TextField txt_userId;

    //Services instances
    EmailService emailService = new EmailService();
    //DB instance
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
        String userId = txt_userId.getText();
        String password = txt_password.getText();
        String confirmPassword = txt_confirmPassword.getText();
        StudentDTO user = new StudentDTO(name, email, userId, password);

        //Check the username
        if (userId.isEmpty()) {
            System.out.println("The user´s username cannot be empty.");
            return;
        }
        //Check the name
        if (name.isEmpty()) {
            System.out.println("The user´s name cannot be empty.");
            return;
        }
        //Check the email
        if (email.isEmpty()) {
            System.out.println("The user´s email cannot be empty.");
            return;
        }
        //Check the password
        if (password.isEmpty()) {
            System.out.println("The user´s password cannot be empty.");
            return;
        }
        //Check both passwords
        if (!password.equals(confirmPassword)) {
            System.out.println("The passwords do not match.");
            return;
        }
        else {
            userDAO.save(user);

            // Execute email sending in a separate thread
            new Thread(() -> {
                emailService.sendRegistrationEmail(email, name); //prueba de correo
            }).start();

            System.out.println("The user has been registered successfully.");
            App.setRoot("LogInView", "ColabEdu -Página principal-");
        }
    }

    @FXML
    void initialize() {
        assert txt_confirmPassword != null : "fx:id=\"txt_confirmPassword\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'RegisterView.fxml'.";
        assert txt_userId != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'RegisterView.fxml'.";

    }

}
