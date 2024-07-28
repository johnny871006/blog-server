package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag getTagByName(String name);

    @Query("SELECT t FROM Tag t")
    List<Tag> findTop(Pageable pageable);
}
