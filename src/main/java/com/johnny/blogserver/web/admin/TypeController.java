package com.johnny.blogserver.web.admin;

import com.johnny.blogserver.model.Type;
import com.johnny.blogserver.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model model) {

        model.addAttribute("page", typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {

        model.addAttribute("type", new Type());
        return "admin/typeInput";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/typeInput";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,
                           BindingResult bindingResult,
                           @PathVariable Long id,
                           RedirectAttributes attributes) {

        Type t1 = typeService.getTypeName(type.getName());

        if(t1 != null) {
            bindingResult.rejectValue("name","nameError","此分類已有");
        }

        if (bindingResult.hasErrors()) {
            return "admin/typeInput";
        }

        Type t2 = typeService.updateType(id,type);

        if (t2 == null) {
            attributes.addFlashAttribute("message","更新失敗");
        } else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes) {

        Type t2 = typeService.getTypeName(type.getName());

        if(t2 != null) {
            bindingResult.rejectValue("name", "nameError","此分類已有!");
        }

        if (bindingResult.hasErrors()) {
            return "admin/typeInput";
        }

        Type t = typeService.saveTyp(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失敗");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","刪除成功");
        return "redirect:/admin/types";
    }


}
