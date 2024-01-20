package org.example.client.Frames.InterfaceAbstractFactory.Message;

import javafx.geometry.Insets;
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
        messageLabel.setMinHeight(USE_PREF_SIZE);
        Label senderLabel = new Label(senderUsername);

        messageLabel.getStyleClass().add("messageText");
        senderLabel.getStyleClass().add("messageSender");

        if (clientUsername.equals(senderUsername)) {
            getStyleClass().add("ownMessage");
            setMargin(this, new Insets(0, 0, 10, 160));
        } else {
            getStyleClass().add("otherMessage");
            setMargin(this, new Insets(0, 0, 10, 0));
        }

        setMaxWidth(180);
        getChildren().add(messageLabel);
        getChildren().add(senderLabel);
        getStyleClass().add("message");
    }
}
