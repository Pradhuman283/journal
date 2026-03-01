package com.algoknight.journalApp.repository;

import com.algoknight.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<UserEntry, ObjectId> {
     UserEntry findByUsername(String username);
}
