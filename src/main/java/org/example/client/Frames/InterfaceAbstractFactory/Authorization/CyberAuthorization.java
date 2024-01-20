package org.example.client.Frames.InterfaceAbstractFactory.Authorization;

import javafx.scene.Scene;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.CyberFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CyberAuthorization extends Authorization{
    @Override
    public void addCss(Scene scene) {
        try {
            File currentFileDirectory = new File(String.valueOf(CyberAuthorization.class.getResource("")));
            String cssPath = currentFileDirectory.getParentFile().getParentFile().getParentFile() + File.separator + "Cyber" + File.separator + "CyberAuthorization.css";
            URL cssUrl = new URL(cssPath);

            scene.getStylesheets().add(cssUrl.toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addFactory(AuthorizationController controller) {
        controller.setFactory(new CyberFactory());
    }
}
