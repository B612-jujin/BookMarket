package kr.ac.kopo.cjj.bookmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController{
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html로 이동
    }

    @GetMapping("/loginfaild")
    public String loginFaild(Model model) {
        model.addAttribute("error","true");
        return "login"; // loginfaild.html로 이동
    }

    @GetMapping("/logout")
    public String logout() {
        return "login"; // logout.html로 이동
    }

}
