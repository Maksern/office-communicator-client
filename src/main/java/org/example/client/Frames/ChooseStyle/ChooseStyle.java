package org.example.client.Frames.ChooseStyle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChooseStyle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getFxml());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getCss().toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getFxml(){
        try {
            File currentFileDirectory = new File(String.valueOf(ChooseStyle.class.getResource("")));
            String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "ChooseStyle" + File.separator + "ChooseStyle.fxml";
            URL fxmlUrl = new URL(fxmlPath);

            return fxmlUrl;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getCss(){
        try {
            File currentFileDirectory = new File(String.valueOf(ChooseStyle.class.getResource("")));
            String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "ChooseStyle" + File.separator + "ChooseStyle.css";
            URL fxmlUrl = new URL(fxmlPath);

            return fxmlUrl;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
