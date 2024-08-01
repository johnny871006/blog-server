package com.johnny.blogserver.web;

import com.johnny.blogserver.service.BlogService;
import com.johnny.blogserver.service.TagService;
import com.johnny.blogserver.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //首頁文章列表
    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {

        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));

        return "index";
    }

    //搜尋文章列表
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {

        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);

        return "search";
    }

    //進入文章詳情頁
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {

        model.addAttribute("blog",blogService.getBlog(id));
        //若使用markdown用下面這個
//        model.addAttribute("blog",blogService.getAndConvert(id));

        return "blog";
    }

    //進入關於我
    @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model) {

        model.addAttribute("newBlogs",blogService.listRecommendBlogTop(3));

        return "_fragments :: newBlogList";
    }
}
