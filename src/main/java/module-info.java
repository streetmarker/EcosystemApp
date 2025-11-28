module com.ecosystem_app.ecosystemapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
//    requires com.ecosystem_app.ecosystemapp;

    opens com.ecosystem_app.ecosystemapp to javafx.fxml;
    exports com.ecosystem_app.ecosystemapp;
    exports com.ecosystem_app.ecosystemapp.objects;

}