module co.uniquindio.estructuras.colabedu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens co.uniquindio.estructuras.colabedu to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu;

    opens co.uniquindio.estructuras.colabedu.Controller to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu.Controller;
}