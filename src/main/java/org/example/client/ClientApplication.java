package org.example.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.client.Frames.Authorization.Authorization;

import java.io.IOException;

public class  ClientApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            new Authorization().start(primaryStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
