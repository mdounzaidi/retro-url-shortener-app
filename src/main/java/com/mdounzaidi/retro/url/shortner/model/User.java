package com.mdounzaidi.retro.url.shortner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("UserList")
@NoArgsConstructor
public class User {
    @Id
    ObjectId id;
    @Indexed(unique = true)
    @NonNull
    String userName;
    @NonNull
    String password;
    @DBRef
    List<URLEntry> urlList=new ArrayList<>();
}
