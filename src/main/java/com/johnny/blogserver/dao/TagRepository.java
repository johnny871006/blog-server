package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag getTagByName(String name);
}
