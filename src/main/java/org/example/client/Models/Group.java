package org.example.client.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Long groupId;
    private String groupName;
    private Timestamp groupCreationDate;
}
