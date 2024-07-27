package com.johnny.blogserver.web.admin;

import com.johnny.blogserver.model.Tag;
import com.johnny.blogserver.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model model) {

        model.addAttribute("page", tagService.listTag(pageable));

        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {

        model.addAttribute("tag", new Tag());
        return "admin/tagInput";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tagInput";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes attributes) {

        Tag t2 = tagService.getTagName(tag.getName());

        if(t2 != null) {
            bindingResult.rejectValue("name", "nameError","此標籤已有!");
        }

        if (bindingResult.hasErrors()) {
            return "admin/tagInput";
        }

        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失敗");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/tags";
    }

//    @PostMapping("/tags/{id}")
//    public String editPost(@Valid Tag tag,
//                           BindingResult bindingResult,
//                           @PathVariable Long id,
//                           RedirectAttributes attributes) {
//
//        Tag t1 = tagService.getTagName(tag.getName());
//
//        if(t1 != null) {
//            bindingResult.rejectValue("name","nameError","此標籤已有");
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "admin/tagInput";
//        }
//
//        Tag t2 = tagService.updateTag(id,tag);
//
//        if (t2 == null) {
//            attributes.addFlashAttribute("message","更新失敗");
//        } else{
//            attributes.addFlashAttribute("message","更新成功");
//        }
//        return "redirect:/admin/tags";
//    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","刪除成功");
        return "redirect:/admin/tags";
    }


}
