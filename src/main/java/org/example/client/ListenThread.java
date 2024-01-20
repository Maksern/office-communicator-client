package org.example.client;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.ClientMenuController;
import org.example.client.Frames.InterfaceAbstractFactory.Message.MessageVBox;
import org.example.client.Models.User;

import java.io.IOException;

public class ListenThread extends Thread{
    private User user;
    private Long chooseFriendId;
    private Long chooseGroupId;
    private ClientMenuController controller;

    public void run(){
        while(!isInterrupted()){

        }
    }

    public void messageFromServerHandler(ClientMenuController controller){
        try {
            String messageFromServer = bufferedReader.readLine();

            String[] messageFromOtherUser = messageFromServer.split(":");
            String messageType = messageFromOtherUser[0];
            Long id = Long.valueOf(messageFromOtherUser[1]);
            String senderUsername = messageFromOtherUser[2];
            String messageText = messageFromOtherUser[3];

            if(messageType.equalsIgnoreCase("friend")){
                System.out.println("Choose friend id = ".concat(chooseFriendId.toString()).concat(". And current friend id = ").concat(id.toString()));
                if(chooseFriendId == id){
                    Platform.runLater(() -> {
                        VBox messageVBox = new MessageVBox(messageText, senderUsername, user.getUserName());
                        controller.getChatHistory().getChildren().add(messageVBox);
                    });
                }
                user.getFriendById(id).increaseNewMessagesCount();

            } else if(messageType.equalsIgnoreCase("group")){
                Long groupId = Long.valueOf(id);
                if (chooseGroupId == groupId){
                    Platform.runLater(() -> {
                        VBox messageVBox = new MessageVBox(messageText, senderUsername, user.getUserName());
                        controller.getChatHistory().getChildren().add(messageVBox);
                    });
                }

                user.getGroupById(groupId).increaseNewMessagesCount();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
