package org.example.client.Frames.Authorization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Authorization extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        File currentFileDirectory = new File(String.valueOf(Authorization.class.getResource("")));
        String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "Authorization.fxml";
        URL fxmlUrl = new URL(fxmlPath);

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
