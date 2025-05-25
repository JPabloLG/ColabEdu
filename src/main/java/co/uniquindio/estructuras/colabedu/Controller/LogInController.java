package co.uniquindio.estructuras.colabedu.Controller;

import co.uniquindio.estructuras.colabedu.App;
import co.uniquindio.estructuras.colabedu.DAO.UserDAOImpl;
import co.uniquindio.estructuras.colabedu.DB.JDBC;
import co.uniquindio.estructuras.colabedu.DTO.StudentDTO;
import co.uniquindio.estructuras.colabedu.Model.AcademicSocialNetwork;
import co.uniquindio.estructuras.colabedu.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    private static final Connection connection = JDBC.getConnection();
    private static UserDAOImpl userDAO = new UserDAOImpl(connection);

    @FXML
    void btn_createAccount(MouseEvent event) throws IOException {
        App.setRoot("RegisterView", "ColabEdu -Registro de Usuario-");
    }

    @FXML
    void btn_logIn(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();

        //Check the password
        if (password.isEmpty()) {
            System.out.println("The password cannot be empty.");
            return;
        }
        //Check the username
        if (username.isEmpty()) {
            System.out.println("The email cannot be empty.");
            return;
        }
            //Search the user in the database
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Create StudentDTO for data transfer
                    StudentDTO userDTO = new StudentDTO(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("user_id"),
                            rs.getString("password")
                    );

                    // Convert StudentDTO to Student for model operations
                    Student student = new Student(
                            userDTO.getName(),
                            userDTO.getEmail(),
                            userDTO.getId(),
                            userDTO.getPassword()// instituto - set to empty string as it's not in the DTO
                    );

                    // Set the current user as Student object
                    AcademicSocialNetwork.getSingleton().setCurrentUser(student);
                    App.setRoot("PrincipalView", "ColabEdu -Página principal-");
                } else {
                    System.out.println("User not found or the credentials are incorrect.");
                    showAlert("Error", "The credentials are not correct, or the user are not registered.");
                }
            } catch (SQLException e) {
                System.out.println("Error in the autentication with DB: " + e.getMessage());
            }
        }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
