module com.mygroup.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.jetbrains.annotations;
    requires java.sql.rowset;
    requires com.google.gson;

    opens com.mygroup.ui to javafx.fxml;
    opens BL to com.google.gson;
    opens DB to com.google.gson;
    exports com.mygroup.ui;


}