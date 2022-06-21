package com.restaurant.controllers;


import com.restaurant.util.EffectUtilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class EmployeeController {

    @FXML
    private BorderPane borderPane;

    private String username;

    @FXML
    public void initialize() {
    }

    public void registerStage(Stage stage) {
        EffectUtilities.makeDraggable(stage, borderPane);
    }

    public void showStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(EmployeeController.class.getResource("/fxml/EmployeeView.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 600, Color.TRANSPARENT);
        Stage employeeStage = new Stage();
        String path = "resources/img/restaurant.png";
        Image applicationIcon = new Image(new File(path).toURI().toString());
        employeeStage.getIcons().add(applicationIcon);
        employeeStage.initStyle(StageStyle.TRANSPARENT);
        employeeStage.setScene(scene);
        EmployeeController controller = loader.getController();
        controller.registerStage(employeeStage);
        employeeStage.show();
    }

    public void logOut() throws IOException {
        borderPane.getScene().getWindow().hide(); // hide Employee stage
        SignInController.showStage(); // show SignIn stage
    }

    public void menu() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/Menu.fxml")));
        borderPane.setCenter(loader);
    }

    public void orders() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/Orders.fxml")));
        borderPane.setCenter(loader);
    }

    public void home() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/Home.fxml")));
        borderPane.setCenter(loader);
    }

    public void codeBook() throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getResource("/fxml/CodeBook.fxml")));
        borderPane.setCenter(loader);
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
