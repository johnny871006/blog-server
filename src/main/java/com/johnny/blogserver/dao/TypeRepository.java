package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type getTypeByName(String name);
}
