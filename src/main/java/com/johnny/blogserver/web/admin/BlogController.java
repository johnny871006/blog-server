package com.johnny.blogserver.web.admin;

import com.johnny.blogserver.dto.BlogQuery;
import com.johnny.blogserver.model.Blog;
import com.johnny.blogserver.model.User;
import com.johnny.blogserver.service.BlogService;
import com.johnny.blogserver.service.TagService;
import com.johnny.blogserver.service.TypeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogInput";
    private static final String LIST = "admin/blogManage";
    private static final String REDIRECT_LIST = "redirect:/admin/blogManage";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogManage")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog,
                        Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));

        return LIST;
    }

    @PostMapping("/blogManage/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog,
                         Model model) {

        model.addAttribute("page", blogService.listBlog(pageable, blog));

//        返回admin/blogManage頁面下的fragment
        return "admin/blogManage :: blogList";
    }

    @GetMapping("/blogManage/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @PostMapping("/blogManage")
    public String post(Blog blog,
                       RedirectAttributes redirectAttributes,
                       HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b = blogService.saveBlog(blog);

        if (b == null) {
            redirectAttributes.addFlashAttribute("message", "操作失敗");
        } else {
            redirectAttributes.addFlashAttribute("message", "操作成功");
        }

        return REDIRECT_LIST;
    }


}
