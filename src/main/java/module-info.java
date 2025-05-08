module co.uniquindio.estructuras.colabedu {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.mail;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens co.uniquindio.estructuras.colabedu to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu;

    opens co.uniquindio.estructuras.colabedu.Controller to javafx.fxml;
    exports co.uniquindio.estructuras.colabedu.Controller;
}