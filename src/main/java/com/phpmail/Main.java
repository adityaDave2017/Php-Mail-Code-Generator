package com.phpmail;

import com.phpmail.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {

        primaryStage.setOnCloseRequest(event -> new MainController().closeClicked());
        primaryStage.setTitle("PHP Mail Code Generator");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainUi.fxml"));
        try {
            BorderPane borderPane = fxmlLoader.load();
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
