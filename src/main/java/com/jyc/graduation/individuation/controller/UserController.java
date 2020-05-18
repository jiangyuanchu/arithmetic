package com.jyc.graduation.individuation.controller;

import com.jyc.graduation.individuation.domain.User;
import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordInfo;
import com.jyc.graduation.individuation.service.RecommendService;
import com.jyc.graduation.individuation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired RecommendService recommendService;

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    /**
     * 退出登录
     */
    @RequestMapping("/exit")
    public ModelAndView exit(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            request.getSession().removeAttribute("user");
        }
        modelAndView.setViewName("redirect:index");
        return modelAndView;

    }

    @PostMapping("/login")
    public ModelAndView login(User user,HttpServletRequest request){
        log.info("用户登录：账号{}，密码{}",user.getAccount(),user.getPassword());
        ModelAndView modelAndView = new ModelAndView();
        User user1 = userService.queryUserByAccountAndPassword(user);
        if (user1 != null){
            request.getSession().setAttribute("user", user1);
            log.info("登录成功");
            UserHistory userHistory = new UserHistory();
            userHistory.setUserId(user.getId());
            List<WordInfo> recommendList = recommendService.getRecommendList(userHistory);
            modelAndView.addObject("recommendList",recommendList);
            modelAndView.setViewName("redirect:index");
            return modelAndView;
        }else{
            modelAndView.addObject("msg","账号或密码错误");
            log.info("登录失败");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(User user){
        log.info("用户注册：{}",user);
        ModelAndView modelAndView = new ModelAndView();
        if (user.getAccount() == null||user.getPassword()==null||user.getName()==null){
            modelAndView.addObject("msg","用户名账号密码不能为空");
            modelAndView.setViewName("register");
            log.info("注册失败");
            return modelAndView;
        }
        User user1 = userService.queryUserByAccount(user.getAccount());
        if (user1 != null){
            modelAndView.addObject("msg","账号已存在");
            modelAndView.setViewName("register");
            log.info("注册失败");
            return modelAndView;
        }
        userService.addUser(user);
        log.info("注册成功");
        modelAndView.setViewName("redirect:login");
        return modelAndView;
    }
}
