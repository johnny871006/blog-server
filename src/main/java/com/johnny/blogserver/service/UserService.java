package com.johnny.blogserver.service;

import com.johnny.blogserver.model.User;

public interface UserService {

    User checkUser(String username, String password);
}
