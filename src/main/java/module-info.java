module com.mslaus.forestapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.mslaus.forestapp to javafx.fxml;
    exports com.mslaus.forestapp;
}