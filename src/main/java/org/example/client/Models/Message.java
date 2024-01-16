package org.example.client.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public Long messageId;
    public String messageText;
    public Timestamp messageCreationDate;
    public User sender;
}
