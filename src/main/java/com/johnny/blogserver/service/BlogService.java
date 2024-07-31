package com.johnny.blogserver.service;

import com.johnny.blogserver.dto.BlogQuery;
import com.johnny.blogserver.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    //得到文章詳細內容(admin的blogInput、index點擊文章)
    Blog getBlog(Long id);

    //makeDown語法轉成html
    Blog getAndConvert(Long id);

    //查詢index文章列表
    Page<Blog> listBlog(Pageable pageable);

    //文章列表查詢篩選 (admin的blogManager、index)
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //右上角搜尋文章列表
    Page<Blog> listBlog(String query,Pageable pageable);

    //文章內含的標籤
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    //有推薦的文章
    List<Blog> listRecommendBlogTop(Integer size);

    //將所有blog存到一個map
    Map<String,List<Blog>> archiveBlog();

    //搜尋總共有多少Blog
    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
