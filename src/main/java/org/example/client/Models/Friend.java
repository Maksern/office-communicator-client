package org.example.client.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private Long friendId;
    private String friendName;
    private List<Message> messageList;
    private int newMessagesCount;

    public static Friend fromUser(User user){
       Friend friend =  new Friend();
       friend.setFriendId(user.getUserId());
       friend.setFriendName(user.getUserName());

       return friend;
    }

    public void increaseNewMessagesCount(){
        newMessagesCount++;
    }
}
