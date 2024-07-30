package com.johnny.blogserver.web;

import com.johnny.blogserver.dto.BlogQuery;
import com.johnny.blogserver.model.Tag;
import com.johnny.blogserver.model.Type;
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

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id,
                        @PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {

        List<Tag> tags = tagService.listTagTop(100);

        if(id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
