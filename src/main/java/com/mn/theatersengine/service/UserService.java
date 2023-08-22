package com.mn.theatersengine.service;

import com.mn.theatersengine.entity.UserEntity;

public interface UserService {

    UserEntity createUser(UserEntity user);

    public boolean checkEmail(String email);
}
