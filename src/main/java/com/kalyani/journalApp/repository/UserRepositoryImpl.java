package com.kalyani.journalApp.repository;

import com.kalyani.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSentimentAnalysis() {
        Query query=new Query();
        /*query.addCriteria(Criteria.where("userName").is("indu"));
        query.addCriteria(Criteria.where("field").lt("value"));
        query.addCriteria(Criteria.where("field").gt("value"));*/

        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
       /* query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("email").ne("").ne(null));*/
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%+-]+\\.[A-Z|a-z]{2,6}$"));
        /*by default it acts like an and operator If you want to make it OR then u ll have to use comma separator as below
        Criteria criteria=new Criteria();
        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true)
                ,Criteria.where("sentimentAnalysis").exists(true)));*/
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }


}
