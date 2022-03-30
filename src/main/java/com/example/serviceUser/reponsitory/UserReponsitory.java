package com.example.serviceUser.reponsitory;

import com.example.serviceUser.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends MongoRepository<User, String> {
    Optional<User> findByName(String name);

}
