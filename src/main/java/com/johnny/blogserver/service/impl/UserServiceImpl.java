package com.johnny.blogserver.service.impl;

import com.johnny.blogserver.dao.UserRepository;
import com.johnny.blogserver.model.User;
import com.johnny.blogserver.service.UserService;
import com.johnny.blogserver.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {

        User user = userRepository.findByUserNameAndPassword(userName, MD5Util.code(password));

        return user;
    }
}
