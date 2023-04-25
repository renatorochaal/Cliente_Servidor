module com.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.restaurante to javafx.fxml;
    exports com.example.restaurante;
    exports com.example.restaurante.Controller;
    opens com.example.restaurante.Controller to javafx.fxml;
}