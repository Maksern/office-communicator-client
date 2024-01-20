package org.example.client.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private  Long userId;
    private  String userName;

    private List<Group> userGroups;
    private List<Friend> userFriends;

    public Friend getFriendById(Long id){
        for (Friend friend: userFriends){
            if (friend.getFriendId().equals(id)){
                return friend;
            }
        }

        return null;
    }

    public Group getGroupById(Long id){
        for (Group group: userGroups){
            if (group.getGroupId().equals(id)){
                return group;
            }
        }

        return null;
    }
}
