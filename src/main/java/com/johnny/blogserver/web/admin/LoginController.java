package com.johnny.blogserver.web.admin;

import com.johnny.blogserver.model.User;
import com.johnny.blogserver.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes) {

        User user = userService.checkUser(userName, password);
        if(user != null){
            user.setPassword(null);
            httpSession.setAttribute("user",user);
            return "admin/index";
        } else{
            redirectAttributes.addFlashAttribute("message","使用者名稱或密碼錯誤!");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
