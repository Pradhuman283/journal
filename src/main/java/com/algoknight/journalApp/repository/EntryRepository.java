package com.algoknight.journalApp.repository;

import com.algoknight.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository  extends MongoRepository<journalEntry, ObjectId> {
}
