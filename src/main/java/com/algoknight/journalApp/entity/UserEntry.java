package com.algoknight.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.algoknight.journalApp.enums.Sentiment;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "User_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntry {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private String email;
    private boolean sentimentalAnalysis;
    private Sentiment sentiment;

    @DBRef
    private List<journalEntry> journalEntries = new ArrayList<>();

    private List<String> roles = new ArrayList<>(List.of("USER"));

}
