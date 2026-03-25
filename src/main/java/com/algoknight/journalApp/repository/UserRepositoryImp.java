package com.algoknight.journalApp.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.algoknight.journalApp.entity.UserEntry;

@Repository
public class UserRepositoryImp {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> getUsersForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        query.addCriteria(Criteria.where("sentimentalAnalysis").is(true));

        // Criteria criteria = new Criteria();
        // criteria.orOperator(
        // Criteria.where("email").exists(true),
        // Criteria.where("sentimentalAnalysis").is(true)
        // );

        // query.addCriteria(criteria);
        List<UserEntry> users = mongoTemplate.find(query, UserEntry.class);
        return users;
    }
}
