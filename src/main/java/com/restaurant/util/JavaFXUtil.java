package com.restaurant.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class JavaFXUtil {

    public static void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Alert");
        alert.setHeaderText(header);
        alert.setContentText(content);
        String path = "resources/img/restaurant.png";
        Image imagePath = new Image(new File(path).toURI().toString());
        DialogPane dialogPane = alert.getDialogPane();
        // promjena stila prozora
        dialogPane.getStylesheets().add(JavaFXUtil.class.getResource("/css/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");
        // dodavanje ikonice
        Stage dialogStage = (Stage) dialogPane.getScene().getWindow();
        dialogStage.getIcons().add(imagePath);
        alert.showAndWait();
    }

    public static void newInputWindow(String name, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(JavaFXUtil.class.getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, Color.TRANSPARENT);
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle(name);
        newStage.initStyle(StageStyle.UTILITY);
        String imgPath = "resources/img/restaurant.png"; // TODO: NOT WORKING
        Image applicationIcon = new Image(new File(imgPath).toURI().toString());
        newStage.getIcons().add(applicationIcon);
        newStage.setScene(scene);
        newStage.showAndWait(); // IMPORTANT
    }

    public static void showInfoAlert(boolean success, String messageType, String messageSuccess, String messageFailure) {
        if (success)
            JavaFXUtil.showAlert(Alert.AlertType.INFORMATION, messageType, messageSuccess);
        else
            JavaFXUtil.showAlert(Alert.AlertType.INFORMATION, messageType, messageFailure);
    }
}
