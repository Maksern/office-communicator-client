package org.example.client.Frames.ClientMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.client.Client;
import org.example.client.Frames.Authorization.Authorization;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void startWithClient(Stage primaryStage, Client client) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getFxmlUrl().openStream());
        root.getStylesheets().add(getCssUrl().toExternalForm());

        ClientMenuController controller = loader.getController();
        controller.setClient(client);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        File currentFileDirectory = new File(String.valueOf(Authorization.class.getResource("")));

        String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "ClientMenu.fxml";
        URL fxmlUrl = new URL(fxmlPath);

        String cssPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "Message.css";
        System.out.println(cssPath);
        URL cssUrl = new URL(cssPath);

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssUrl.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public URL getFxmlUrl(){
        try {
            File currentFileDirectory = new File(String.valueOf(Authorization.class.getResource("")));

            String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "ClientMenu.fxml";
            URL fxmlUrl = new URL(fxmlPath);

            return fxmlUrl;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getCssUrl(){
        try {
            File currentFileDirectory = new File(String.valueOf(Authorization.class.getResource("")));

            String cssPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "Message.css";
            URL cssUrl = new URL(cssPath);

            return cssUrl;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
