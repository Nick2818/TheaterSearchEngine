package com.mn.theatersengine.controller;

import com.mn.theatersengine.entity.UserEntity;
import com.mn.theatersengine.repository.UserRepository;
import com.mn.theatersengine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        if(p!=null){
            String email = p.getName();
            UserEntity user = userRepository.findByEmail(email);
            m.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserEntity user, HttpSession session){
        boolean f = userService.checkEmail(user.getEmail());

        if(f){
            session.setAttribute("msg", "Email already exists!");
        }else{
            UserEntity userEntity = userService.createUser(user);
            if(userEntity != null){
                session.setAttribute("msg", "Register Successfully");
            }else{
                session.setAttribute("msg", "Something wrong on server, try again later!");
            }
        }

        return "redirect:/register";
    }


}
