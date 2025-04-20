package co.uniquindio.estructuras.colabedu.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_search;

    @FXML
    void btn_colabEdu(MouseEvent event) {

    }

    @FXML
    void btn_contenidos(MouseEvent event) {

    }

    @FXML
    void btn_crear(MouseEvent event) {
        try {
            // Cargar el FXML de la pestaña/ventana de subir contenido
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/uniquindio/estructuras/colabedu/LoadContentView.fxml"));
            Parent root = fxmlLoader.load();

            // Crear nuevo Stage (ventana)
            Stage stage = new Stage();
            stage.setTitle("ColabEdu -Crea contenido-");
            stage.setScene(new Scene(root));

            // Opcional: bloquear la principal hasta cerrar esta
            // stage.initModality(Modality.WINDOW_MODAL);
            // stage.initOwner(((Node) event.getSource()).getScene().getWindow());

            stage.show(); // Mostrar la ventana sin cerrar la principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_grupos(MouseEvent event) {

    }

    @FXML
    void btn_perfil(MouseEvent event) {

    }

    @FXML
    void btn_principal(MouseEvent event) {

    }

    @FXML
    void btn_soliAyuda(MouseEvent event) {

    }

    @FXML
    void btn_solicitudesAyuda(MouseEvent event) {

    }

    @FXML
    void btn_valoraciones(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'PrincipalView.fxml'.";

    }

}
