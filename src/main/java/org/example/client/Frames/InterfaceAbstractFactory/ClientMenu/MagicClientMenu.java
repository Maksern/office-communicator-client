package org.example.client.Frames.InterfaceAbstractFactory.ClientMenu;


import javafx.scene.Scene;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class MagicClientMenu extends ClientMenu {
    @Override
    public void addCssClientMenu(Scene scene){
        try {
            File currentFileDirectory = new File(String.valueOf(MagicClientMenu.class.getResource("")));
            String cssPath = currentFileDirectory.getParentFile().getParentFile().getParentFile() + File.separator + "Magic" + File.separator + "MagicClientMenu.css";
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
            String cssPath = currentFileDirectory.getParentFile().getParentFile().getParentFile() + File.separator + "Magic" + File.separator + "MagicMessage.css";
            URL cssUrl = new URL(cssPath);

            scene.getStylesheets().add(cssUrl.toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
