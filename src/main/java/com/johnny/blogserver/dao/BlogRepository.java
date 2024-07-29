package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("SELECT b FROM Blog b WHERE b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    // select * from t_blog where title like '%內容%'
    @Query("SELECT b FROM Blog b WHERE b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);
}
