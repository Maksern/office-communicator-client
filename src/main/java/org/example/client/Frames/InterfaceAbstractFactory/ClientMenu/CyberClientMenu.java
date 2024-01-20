package org.example.client.Frames.InterfaceAbstractFactory.ClientMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.client.Client;
import org.example.client.Models.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class CyberClientMenu extends ClientMenu {

    @Override
    public void addCssClientMenu(Scene scene){
        try {
            File currentFileDirectory = new File(String.valueOf(MagicClientMenu.class.getResource("")));
            String cssPath = currentFileDirectory.getParentFile().getParentFile().getParentFile() + File.separator + "Cyber" + File.separator + "CyberClientMenu.css";
            URL cssUrl = new URL(cssPath);

            scene.getStylesheets().add(cssUrl.toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCssMessage(Scene scene){
        try {
            File currentFileDirectory = new File(String.valueOf(MagicClientMenu.class.getResource("")));
            String cssPath = currentFileDirectory.getParentFile().getParentFile().getParentFile() + File.separator + "Cyber" + File.separator + "CyberMessage.css";
            URL cssUrl = new URL(cssPath);

            scene.getStylesheets().add(cssUrl.toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
