module org.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.net.http;
    requires java.sql;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    exports org.example.client;
    opens org.example.client to javafx.fxml;

    exports org.example.client.Models;
    opens org.example.client.Models to javafx.fxml;

    exports org.example.client.Frames.InterfaceAbstractFactory.Message;
    opens org.example.client.Frames.InterfaceAbstractFactory.Message to javafx.fxml;

    exports org.example.client.Frames.ChooseStyle;
    opens org.example.client.Frames.ChooseStyle to javafx.fxml;

    exports org.example.client.Frames.InterfaceAbstractFactory.Authorization;
    opens org.example.client.Frames.InterfaceAbstractFactory.Authorization to javafx.fxml;

    exports org.example.client.Frames.InterfaceAbstractFactory.ClientMenu;
    opens org.example.client.Frames.InterfaceAbstractFactory.ClientMenu to javafx.fxml;
}