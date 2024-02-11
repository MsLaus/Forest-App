module com.mslaus.forestapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens com.mslaus.forestapp to javafx.fxml;
    exports com.mslaus.forestapp;
    exports com.mslaus.forestapp.Controllers.ViewControllers;
}