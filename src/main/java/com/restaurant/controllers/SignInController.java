package com.restaurant.controllers;

import com.restaurant.util.DBUtil;
import com.restaurant.util.EffectUtilities;
import com.restaurant.util.JavaFXUtil;
import com.restaurant.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class SignInController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private static Integer userId;

    // https://stackoverflow.com/questions/18792822/dragging-an-undecorated-stage-in-javafx
    public void registerStage(Stage stage) {
        EffectUtilities.makeDraggable(stage, borderPane);
    }

    public void exitSignInWindow() {
        System.exit(0);
    }

    public void signIn() {
        boolean signIn;
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.equals("") || password.equals("")) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, Util.NO_PARAMS);
            return;
        }
        try {
            String result = DBUtil.signIn(username, password);
            signIn = Boolean.parseBoolean(result.split("#")[0]);
            userId = Integer.parseInt(result.split("#")[1]);
            if (signIn) {
                borderPane.getScene().getWindow().hide(); // hide SignIn stage
                EmployeeController employeeController = new EmployeeController();
                employeeController.setUsername(username); // setting username of current user to controller TODO: DAO Employee
                employeeController.showStage(); // show Employee stage
            } else {
                JavaFXUtil.showAlert(Alert.AlertType.ERROR, "Sign In", "Enter valid parameters");
                return;
            }
        } catch (Exception ex) {
            JavaFXUtil.showAlert(Alert.AlertType.ERROR, Util.ERROR, ex.getMessage());
            ex.printStackTrace();
            return;
        }
    }

    public static void showStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(SignInController.class.getResource("/fxml/SignIn.fxml"));
        Scene scene = new Scene(loader.load(), 600, 600, Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Restaurant");
        String path = "resources/img/restaurant.png";
        Image applicationIcon = new Image(new File(path).toURI().toString());
        stage.getIcons().add(applicationIcon);
        stage.setScene(scene);
        stage.setResizable(false);
        SignInController controller = loader.getController();
        controller.registerStage(stage);
        stage.show();
    }

    public static Integer getUserId() {
        return userId;
    }
}
