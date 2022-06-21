module com.example.javafxdemo {
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

    opens com.restaurant.controllers to javafx.fxml;
    exports com.restaurant.controllers;
    exports com.restaurant;
    opens com.restaurant to javafx.fxml;
    exports com.restaurant.util;
    opens com.restaurant.util to javafx.fxml;
    opens com.restaurant.models to javafx.base;
}