package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type getTypeByName(String name);

    @Query("SELECT t FROM Type t")
    List<Type> findTop(Pageable pageable);
}
