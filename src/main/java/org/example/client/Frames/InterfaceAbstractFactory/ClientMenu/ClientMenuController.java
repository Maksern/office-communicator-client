package org.example.client.Frames.InterfaceAbstractFactory.ClientMenu;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client.Client;
import org.example.client.Frames.InterfaceAbstractFactory.Elements.FriendElementHBox;
import org.example.client.Frames.InterfaceAbstractFactory.Elements.GroupElementHBox;
import org.example.client.Frames.InterfaceAbstractFactory.Message.MessageVBox;
import org.example.client.Models.Friend;
import org.example.client.Models.Group;
import org.example.client.Models.Message;
import org.example.client.Models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static javafx.scene.layout.VBox.setMargin;

@Data
@NoArgsConstructor
public class ClientMenuController {
    @Setter
    private Client client;

    @FXML
    private ScrollPane friendsScroll;

    @FXML
    private ScrollPane groupsScroll;

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
            String clientUsername = client.getUser().getUserName();
            VBox messageVBox = new MessageVBox(message, clientUsername, clientUsername);
            chatHistory.getChildren().add(messageVBox);
            messageText.clear();

            //add Message to Friend or Group
            String messageWithUsername = clientUsername.concat(":").concat(message);
            String messageToServer = concatStrings(List.of("writeMessage", messageWithUsername, client.getUser().getUserId(), client.getChooseFriendId(), client.getChooseGroupId()));
            client.sendMessage(messageToServer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFriends() throws IOException {
        System.out.println("Ask to get Friends");
        groupsScroll.setVisible(false);
        friendsVBox.getChildren().removeAll(friendsVBox.getChildren());
        
        if (client.getUser().getUserFriends() == null) {
            client.stopListenForMessage();

            String messageToServer = concatStrings(List.of("getFriends", client.getUser().getUserId()));
            client.sendMessage(messageToServer);
            String friends = client.getBufferedReader().readLine();
            List<User> userList = new ObjectMapper().readValue(friends, new TypeReference<>() {});
            List<Friend> friendList = StreamSupport.stream(userList.spliterator(), false)
                            .map(user -> Friend.fromUser(user))
                            .collect(Collectors.toList());
            client.getUser().setUserFriends(friendList);
        }


        for (Friend friend: client.getUser().getUserFriends()){
            FriendElementHBox friendElement = new FriendElementHBox(friend);
            friendElement.addEventFilter(MouseEvent.MOUSE_PRESSED, chooseFriend(friend.getFriendId()));
            setMargin(friendElement, new Insets(0, 0, 10, 0));
            friendsVBox.getChildren().add(friendElement);
        }


        friendsScroll.setVisible(true);
    }

    public void getGroups() throws IOException {
        friendsScroll.setVisible(false);
        groupsVBox.getChildren().removeAll(groupsVBox.getChildren());
        if (client.getUser().getUserGroups() == null) {
            client.stopListenForMessage();

            String messageToServer = concatStrings(List.of("getGroups", client.getUser().getUserId()));
            client.sendMessage(messageToServer);
            String groupsData = client.getBufferedReader().readLine();
            List<Group> groupList = new ObjectMapper().readValue(groupsData, new TypeReference<>() {});

            client.getUser().setUserGroups(groupList);
        }

        for (Group group: client.getUser().getUserGroups()) {
            GroupElementHBox groupElement = new GroupElementHBox(group);
            setMargin(groupElement, new Insets(0, 0, 10, 0));
            groupElement.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseGroup(group.getGroupId()));
            groupsVBox.getChildren().add(groupElement);
        }

        groupsScroll.setVisible(true);
    }

    public EventHandler<MouseEvent> chooseGroup(Long groupId){
        return mouseEvent ->{
            client.setChooseGroupId(groupId);
            client.setChooseFriendId(-1L);
            showMessageHistory(groupId, -1L);
        };
    }

    public EventHandler<MouseEvent> chooseFriend(Long friendId){
        return mouseEvent -> {
            System.out.println("Choose friend with id = ".concat(friendId.toString()));
            client.setChooseGroupId(-1L);
            client.setChooseFriendId(friendId);
            showMessageHistory(-1L, friendId);
        };
    }

    public void showMessageHistory(Long groupID, Long friendId){
            client.stopListenForMessage();

            List<Message> chatHistoryMessages = new ArrayList<>();

            if (friendId != -1) {
                List<Message> friendMessageList = getFriendMessageList(friendId);
                chatHistoryMessages = friendMessageList;

            } else if (groupID != -1) {
                List<Message> groupMessageList = getGroupMessageList(groupID);
                chatHistoryMessages = groupMessageList;
            }

            chatHistory.getChildren().removeAll(chatHistory.getChildren());
            addToChatHistory(chatHistoryMessages);
            client.listenForMessage(this);
    }

    public List<Message> getGroupMessageList(Long groupID){
        try {
            Group group = client.getUser().getGroupById(groupID);
            List<Message> groupMessageList = group.getGroupMessages();
            if(groupMessageList == null){
                String messageToServer = concatStrings(List.of("getAllMessagesInGroup", groupID));
                client.sendMessage(messageToServer);
                String chatMessages = client.getBufferedReader().readLine();
                groupMessageList = new ObjectMapper().readValue(chatMessages, new TypeReference<>() {});
                group.setGroupMessages(groupMessageList);
            }

            return groupMessageList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getFriendMessageList(Long friendId){
        try {
            Friend friend = client.getUser().getFriendById(friendId);
            List<Message> friendMessageList = friend.getMessageList();
            if(friendMessageList == null){
                String messageToServer = concatStrings(List.of("getMessagesBetweenTwoUsers", client.getUser().getUserId(), friendId));
                client.sendMessage(messageToServer);
                String chatMessages = client.getBufferedReader().readLine();
                friendMessageList = new ObjectMapper().readValue(chatMessages, new TypeReference<>() {});
                friend.setMessageList(friendMessageList);
            }

            return friendMessageList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToChatHistory(List<Message> messageList){
        for (Message message: messageList){
            VBox messageVbox = new MessageVBox(message.getMessageText(), message.getSender().getUserName(), client.getUser().getUserName());
            chatHistory.getChildren().add(messageVbox);
        }
    }

    public void disableButton(Button button, int delay){
        button.setDisable(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {@Override public void run() {button.setDisable(false);}}, delay);
    }

    public String concatStrings(List<Object> objects){
        objects = new ArrayList<>(objects);
        String result = "";

        int lastIndex = objects.size()-1;
        String last = objects.get(lastIndex).toString();
        objects.remove(lastIndex);

        for (Object obj: objects){
            String text = obj.toString();
            result += text + "/";
        }

        result += last;

        return result;
    }

}

