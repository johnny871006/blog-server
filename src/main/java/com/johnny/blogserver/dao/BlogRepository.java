package com.johnny.blogserver.dao;

import com.johnny.blogserver.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("SELECT b FROM Blog b WHERE b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    // select * from t_blog where title like '%內容%'
    @Query("SELECT b FROM Blog b WHERE b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Blog b SET b.views = b.views+1 WHERE b.id= ?1")
    void updateViews(Long id);

    //查詢年份並分組
    @Query("SELECT function('date_format', b.updateTime,'%Y') as year FROM Blog b " +
            "GROUP BY function('date_format', b.updateTime,'%Y') " +
            "ORDER BY function('date_format', b.updateTime,'%Y') DESC ")
    List<String> findGroupYear();

    @Query("SELECT b FROM Blog b WHERE function('date_format', b.updateTime, '%Y') = ?1 ")
    List<Blog> findByYear(String year);
}
