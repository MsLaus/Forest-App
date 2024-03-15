module com.mslaus.forestapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires org.postgresql.jdbc;

    opens com.mslaus.forestapp.controllers.viewControllers to javafx.fxml;
    opens com.mslaus.forestapp to javafx.fxml;
    opens com.mslaus.forestapp.controllers.itemControllers;


    exports com.mslaus.forestapp;
    exports com.mslaus.forestapp.controllers.viewControllers;
    exports com.mslaus.forestapp.controllers.itemControllers;
}