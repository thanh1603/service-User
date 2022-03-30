package com.example.serviceUser.rpc;


import com.example.serviceUser.*;
import com.example.serviceUser.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserGrpcserver extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;


    @Override
    public void createUser(UserRequest request, StreamObserver<UserResponse> responseObserver){
            responseObserver.onNext(userService.createUser(request));
            responseObserver.onCompleted();


    }


    @Override
    public void loginUser(UserRequestLogin request, StreamObserver<UserResponseLogin> responseObserver){
        responseObserver.onNext(userService.loginUser(request));
        responseObserver.onCompleted();

    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UpdateUserResponse> responseStreamObserver) {
        responseStreamObserver.onNext(userService.updateUser(request));
        responseStreamObserver.onCompleted();
    }


    @Override
    public void updateUserPost(UpdateUserPostRequest request, StreamObserver<UpdateUserPostResponse> responseStreamObserver){
        responseStreamObserver.onNext(userService.updateUserPost(request));
        responseStreamObserver.onCompleted();
    }

}
