package co.uniquindio.estructuras.colabedu.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertService {

    public static void showAlert(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void showInfo(String mensaje) {
        showAlert(AlertType.INFORMATION, "Información", mensaje);
    }

    public static void showError(String mensaje) {
        showAlert(AlertType.ERROR, "Error", mensaje);
    }

    public static void showWarning(String mensaje) {
        showAlert(AlertType.WARNING, "Advertencia", mensaje);
    }
}