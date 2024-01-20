package org.example.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.client.Frames.ChooseStyle.ChooseStyle;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.AbstractFactory;
import org.example.client.Frames.InterfaceAbstractFactory.FactoryProducer;

import java.io.IOException;

public class  ClientApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new ChooseStyle().start(primaryStage);
    }
}
