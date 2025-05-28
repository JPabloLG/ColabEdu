package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.DAO.UserDAO;
import co.uniquindio.estructuras.colabedu.DAO.UserDAOImpl;
import co.uniquindio.estructuras.colabedu.DB.JDBC;
import co.uniquindio.estructuras.colabedu.DTO.StudentDTO;
import co.uniquindio.estructuras.colabedu.Model.AcademicSocialNetwork;
import co.uniquindio.estructuras.colabedu.Model.Student;
import co.uniquindio.estructuras.colabedu.Util.AlertService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


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

    private static final Student currentUser = AcademicSocialNetwork.getSingleton().getCurrentUser();

    @FXML
    void btn_back(MouseEvent event) throws IOException {
        App.setRoot("PrincipalView", "ColabEdu -Página principal-");
    }

    @FXML
    void btn_confirm(MouseEvent event) throws IOException {
        String password = txt_password.getText();
        String newPassword = txt_newPassword.getText();
        String confirmPassword = txt_confirmPassword.getText();
        String name = txt_name.getText();
        String email = txt_email.getText();
        String username = txt_username.getText();

        if (verifyPassword()){
            // Get database connection
            Connection connection = JDBC.getConnection();
            UserDAO userDAO = new UserDAOImpl(connection);

            // Create StudentDTO with updated information
            StudentDTO updatedUser = new StudentDTO(
                    name,
                    email,
                    currentUser.getId(),
                    newPassword.isEmpty() ? currentUser.getPassword() : newPassword
            );

            // Update user in database
            userDAO.update(updatedUser);

            // Update current user in memory
            currentUser.setName(name);
            currentUser.setEmail(email);
            if (!newPassword.isEmpty()) {
                currentUser.setPassword(newPassword);
            }

            AlertService.showInfo("Los datos se cambiaron correctamente");
            App.setRoot("PrincipalView", "ColabEdu -Página principal-");
        }
    }

    private boolean verifyPassword() {
        String password = txt_password.getText();
        String newPassword = txt_newPassword.getText();
        String confirmPassword = txt_confirmPassword.getText();

        if (password.equals(currentUser.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                return true;
            }else {
                AlertService.showError("The passwords do not match. Please try again.");
                return false;
            }
        }else{
            AlertService.showError("The password is incorrect. Please try again.");
            return false;
        }
    }

    @FXML
    void initialize() {
        // Set text fields with current user information
        txt_email.setText(currentUser.getEmail());
        txt_username.setText(currentUser.getId()); // Using ID as username
        txt_name.setText(currentUser.getName());

        assert txt_confirmPassword != null : "fx:id=\"txt_confirmPassword\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_newPassword != null : "fx:id=\"txt_newPassword\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_password != null : "fx:id=\"txt_password\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
        assert txt_username != null : "fx:id=\"txt_username\" was not injected: check your FXML file 'ProfileSettingsView.fxml'.";
    }
}