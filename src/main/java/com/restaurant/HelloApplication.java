package com.restaurant;

import com.restaurant.controllers.SignInController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SignInController.showStage();
    }

    public static void main(String[] args) {
        launch();
    }
}