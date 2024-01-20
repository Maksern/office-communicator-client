package org.example.client.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Long groupId;
    private String groupName;
    private Timestamp groupCreationDate;

    private List<Message> groupMessages;
    private int newMessagesCount;

    public void increaseNewMessagesCount(){
        newMessagesCount++;
    }
}
