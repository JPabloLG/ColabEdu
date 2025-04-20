module co.uniquindio.estructuras.colabedu {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.uniquindio.estructuras.colabedu to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu;

    opens co.uniquindio.estructuras.colabedu.Controller to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu.Controller;
}