package com.example.serviceUser.service;

import com.example.serviceUser.*;
import com.example.serviceUser.domain.model.User;
import com.example.serviceUser.reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserReponsitory userReponsitory;

    public UserResponse createUser(UserRequest request) {
        if (request != null) {
            Optional<User> userOp = userReponsitory.findByName(request.getName());
            if (userOp.isPresent()) {
                return UserResponse.newBuilder()
                        .setMessage("User taken already")
                        .build();
            } else {
                User newUser = new User();
                newUser.setName(request.getName());
                newUser.setEmail(request.getEmail());
                newUser.setPassword(request.getPassword());
                userReponsitory.save(newUser);

                return UserResponse.newBuilder()
                        .setName(request.getName())
                        .setEmail(request.getEmail())
                        .setPassword(request.getPassword())
                        .build();
            }


        }
        return UserResponse.newBuilder()
                .setMessage("Please transmit data")
                .build();

    }


    public UserResponseLogin loginUser(UserRequestLogin request) {
        User user = userReponsitory.findByName(request.getName())
                .orElseThrow(() -> new NullPointerException("User Not Found with username: " + request.getName()));
        return UserResponseLogin.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .build();
    }


    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        User user = userReponsitory.findById(request.getId())
                .orElseThrow(() -> new NullPointerException("User Not Found with username: " + request.getName()));
        if (user != null) {

//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setName(request.getName());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());

            userReponsitory.save(user);

            return UpdateUserResponse.newBuilder()
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setMessage("Update user success!")
                    .build();

        }
        return UpdateUserResponse.newBuilder()
                .setMessage("you do not have permission")
                .build();
    }


    public UpdateUserPostResponse updateUserPost(UpdateUserPostRequest request) {
        User user = userReponsitory.findById(request.getIdUser())
                .orElseThrow(() -> new NullPointerException("User Not Found with username: " + request.getIdUser()));
        if (user!= null) {
            List<String> listIdPost = Optional.ofNullable(user.getListPostId())
                    .orElse(new ArrayList<>());
            listIdPost.add(request.getIdPost());
            user.setListPostId(listIdPost);
            userReponsitory.save(user);

            return UpdateUserPostResponse.newBuilder()
                    .setIdUser(user.getId())
                    .setIdPost(request.getIdPost())
                    .setMessage("add id post for user success")
                    .build();

        }
        return UpdateUserPostResponse.newBuilder()
                .setMessage("you do not have permission")
                .build();
    }


}
