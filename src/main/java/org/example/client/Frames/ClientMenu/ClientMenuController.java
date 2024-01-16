package org.example.client.Frames.ClientMenu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client.Client;
import org.example.client.Frames.Message.MessageVBox;
import org.example.client.Models.Group;
import org.example.client.Models.Message;
import org.example.client.Models.User;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Data
@NoArgsConstructor
public class ClientMenuController {
    @Setter
    private Client client;
    private Long chooseUserId;
    private Long chooseGroupId;

    @FXML
    private Button sendButton;

    @FXML
    private VBox friendsVBox;

    @FXML
    private VBox groupsVBox;

    @FXML
    private TextField messageText;

    @FXML
    private VBox chatHistory;

    public void sendMessage() {
        try {
            disableButton(sendButton, 2000);

            String message = messageText.getText();
            String clientUsername = client.getClientUsername();
            VBox messageVBox = new MessageVBox(message, clientUsername, clientUsername);
            chatHistory.getChildren().add(messageVBox);
            messageText.clear();

            client.sendMessage("writeMessage/".concat(message).concat("/").concat(client.getClientID().toString()).concat("/").concat(chooseUserId.toString()).concat("/").concat(chooseGroupId.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFriends() throws IOException {
        if(client.isKeepListening()){
            client.sendMessage("closeChat");
            client.stopListenForMessage();
        }

        groupsVBox.setVisible(false);
        friendsVBox.getChildren().removeAll(friendsVBox.getChildren());
        client.sendMessage("getFriends".concat("/").concat(client.getClientID().toString()));
        String friends = client.getBufferedReader().readLine();
        List<User> userList = new ObjectMapper().readValue(friends, new TypeReference<>() {});
        ToggleGroup friendsGroup = new ToggleGroup();

        for (User user: userList){
            RadioButton rb = new RadioButton(user.getUserName());
            rb.setToggleGroup(friendsGroup);
            setActionOnSelected(rb, -1L, user.getUserId());
            friendsVBox.getChildren().add(rb);
        }

        friendsVBox.setVisible(true);
    }

    public void getGroups() throws IOException {
        if(client.isKeepListening()){
            client.sendMessage("closeChat");
            client.stopListenForMessage();
        }

        friendsVBox.setVisible(false);
        groupsVBox.getChildren().removeAll(groupsVBox.getChildren());

        client.sendMessage("getGroups".concat("/").concat(client.getClientID().toString()));
        String groupsData = client.getBufferedReader().readLine();
        List<Group> groupList = new ObjectMapper().readValue(groupsData, new TypeReference<>() {
        });
        ToggleGroup groupToggleGroup = new ToggleGroup();

        for (Group group: groupList) {
            RadioButton rb = new RadioButton(group.getGroupName());
            rb.setToggleGroup(groupToggleGroup);
            setActionOnSelected(rb, group.getGroupId(), -1L);
            groupsVBox.getChildren().add(rb);
        }

        groupsVBox.setVisible(true);
    }

    public void setActionOnSelected(RadioButton rb, Long groupID, Long userID){
        rb.setOnAction(event -> {
            if (rb.isSelected()) {
                try {
                    if(client.isKeepListening()){
                        client.sendMessage("closeChat");
                        client.stopListenForMessage();
                    }

                    setChooseUserId(userID);
                    setChooseGroupId(groupID);

                    if (chooseUserId != -1) {
                        client.sendMessage("getMessagesBetweenTwoUsers/".concat(String.valueOf(client.getClientID())).concat("/").concat(String.valueOf(userID)));
                    } else if (chooseGroupId != -1) {
                        client.sendMessage("getAllMessagesInGroup/".concat(String.valueOf(groupID)));
                    }

                    String chatHistoryMessages = client.getBufferedReader().readLine();
                    addToChatHistory(chatHistoryMessages);
                    client.listenForMessage(chatHistory);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addToChatHistory(String messages){
        try {
            List<Message> messageList = new ObjectMapper().readValue(messages, new TypeReference<>() {});
            for (Message message: messageList){
                VBox messageVbox = new MessageVBox(message.getMessageText(), message.getSender().getUserName(), client.getClientUsername());
                chatHistory.getChildren().add(messageVbox);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void disableButton(Button button, int delay){
        button.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {@Override public void run() {button.setDisable(false);}}, delay);
    }
}

