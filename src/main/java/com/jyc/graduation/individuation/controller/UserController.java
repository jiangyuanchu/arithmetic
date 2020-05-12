package com.jyc.graduation.individuation.controller;

import com.jyc.graduation.individuation.domain.User;
import com.jyc.graduation.individuation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("login")
    public String login(String account,String password, Model model,HttpServletRequest request){
        User user = userService.queryUserByAccountAndPassword(account,password);
        if (user != null){
            request.getSession().setAttribute("user", user);
            return "redirect:index";
        }else{
            model.addAttribute("msg","账号或密码错误");
            return "login";
        }
    }

    @GetMapping("register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("register")
    public String register(@RequestBody User user,Model model){
        User user1 = userService.queryUserByAccount(user.getAccount());
        if (user1 != null){
            model.addAttribute("msg","账号已存在");
            return "register";
        }
        userService.addUser(user);
        return "redirect:index";
    }
}