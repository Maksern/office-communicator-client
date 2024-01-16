package org.example.client.Frames.Message;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageVBox extends VBox {
    private final String text;
    private final String senderUsername;

    public MessageVBox(String text, String senderUsername, String clientUsername) {
        this.text = text;
        this.senderUsername = senderUsername;

        initMessage(clientUsername);
    }

    private void initMessage(String clientUsername) {
        Label messageLabel = new Label(text);
        Label senderLabel = new Label(senderUsername);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(200);
        getStyleClass().add("message");

        if (clientUsername.equals(senderUsername)) {
            getStyleClass().add("ownMessage");
        } else {
            getStyleClass().add("otherMessage");
        }

        getChildren().add(messageLabel);
        getChildren().add(senderLabel);
    }
}
