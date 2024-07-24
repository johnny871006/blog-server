package com.johnny.blogserver.web.admin;

import com.johnny.blogserver.dto.BlogQuery;
import com.johnny.blogserver.model.Blog;
import com.johnny.blogserver.service.BlogService;
import com.johnny.blogserver.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogManage")
    public String blogs(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));

        return "admin/blogManage";
    }

    @PostMapping("/blogManage/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog,
                        Model model){

        model.addAttribute("page",blogService.listBlog(pageable,blog));

//        返回admin/blogManage頁面下的fragment
        return "admin/blogManage :: blogList";
    }


}
