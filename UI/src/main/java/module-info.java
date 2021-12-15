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

    opens com.mygroup.ui to javafx.fxml;
    exports com.mygroup.ui;
}