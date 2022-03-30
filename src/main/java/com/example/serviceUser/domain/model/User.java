package com.example.serviceUser.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<String> groupId;
    private List<String> friendId;
    private List<String> listPostId;
    private String  role;
    private String roleGroup;
}
