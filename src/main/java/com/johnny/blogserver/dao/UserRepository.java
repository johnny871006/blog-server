package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameAndPassword(String userName,String password);
}
