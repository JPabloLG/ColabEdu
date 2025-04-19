package co.uniquindio.estructuras.colabedu.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrincipalController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_search;

    @FXML
    void btn_colabEdu(MouseEvent event) {
        System.out.println("Boton ColabEdu");
    }

    @FXML
    void btn_contenidos(MouseEvent event) {
        System.out.println("Boton contenidos");
    }

    @FXML
    void btn_grupos(MouseEvent event) {
        System.out.println("Boton grupos");
    }

    @FXML
    void btn_perfil(MouseEvent event) {
        System.out.println("Boton perfil");
    }

    @FXML
    void btn_principal(MouseEvent event) {
        System.out.println("Boton principal");
    }

    @FXML
    void btn_valoraciones(MouseEvent event) {
        System.out.println("Boton valoraciones");
    }

    @FXML
    void btn_soliAyuda(MouseEvent event) {
        System.out.println("Boton solicitar ayuda");
    }

    @FXML
    void btn_solicitudesAyuda(MouseEvent event) {
        System.out.println("Boton solicitudes de ayuda");
    }

    @FXML
    void initialize() {
        assert txt_search != null : "fx:id=\"txt_search\" was not injected: check your FXML file 'PrincipalView.fxml'.";

    }

}
