package com.johnny.blogserver.service;

import com.johnny.blogserver.model.User;

public interface UserService {

    User checkUser(String userName, String password);
}
