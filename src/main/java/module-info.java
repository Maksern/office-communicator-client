module org.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.net.http;
    requires java.sql;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports org.example.client;
    opens org.example.client to javafx.fxml;
    exports org.example.client.Models;
    opens org.example.client.Models to javafx.fxml;
    exports org.example.client.Frames.Authorization;
    opens org.example.client.Frames.Authorization to javafx.fxml;
    exports org.example.client.Frames.ClientMenu;
    opens org.example.client.Frames.ClientMenu to javafx.fxml;
    exports org.example.client.Frames.Message;
    opens org.example.client.Frames.Message to javafx.fxml;
}