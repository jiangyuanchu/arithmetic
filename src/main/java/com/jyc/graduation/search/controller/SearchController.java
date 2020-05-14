package com.jyc.graduation.search.controller;

import com.jyc.graduation.individuation.domain.User;
import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.service.UserHistoryService;
import com.jyc.graduation.search.domain.SearchResult;
import com.jyc.graduation.search.pageprocessor.BaiduPageProcessor;
import com.jyc.graduation.search.pageprocessor.LuoMaPanPageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class SearchController {
    @Autowired UserHistoryService userHistoryService;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/baiduSearch")
    public ModelAndView baiduSearch(String words, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        log.info("获取用户{}",user);
        if (user != null){
            UserHistory userHistory = new UserHistory();
            userHistory.setUserId(user.getId());
            userHistory.setWord(words);
            userHistoryService.searchHistory(userHistory);
        }
        Spider spider = Spider.create(new BaiduPageProcessor()).thread(5);
        try{
            //获取这个链接下的数据
            String urlTemplate = "http://baidu.com/s?wd=%s&rsv_spt=1&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8";
            //传入搜索关键字进行爬虫抓取
            ResultItems resultItems = spider.<ResultItems>get(String.format(urlTemplate, words));

            List<SearchResult> list = resultItems.get("result");

            modelAndView.addObject("resultList",list);
            modelAndView.setViewName("search");
            return modelAndView;
        }finally {
            spider.close();
        }

    }

    @RequestMapping("/soupanSearch")
    public ModelAndView soupanSearch(String words,String page, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            UserHistory userHistory = new UserHistory();
            userHistory.setUserId(user.getId());
            userHistory.setWord(words);
            userHistoryService.searchHistory(userHistory);
        }
        Spider spider = Spider.create(new LuoMaPanPageProcessor()).thread(5);
        try{
            //获取这个链接下的数据
            String urlTemplate = "https://www.luomapan.com/search?keyword=%s&page=%s";
            //传入搜索关键字进行爬虫抓取
            ResultItems resultItems = spider.<ResultItems>get(String.format(urlTemplate,words,page));

            List<SearchResult> list = resultItems.get("result");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("resultList",list);
            modelAndView.setViewName("search");
            return modelAndView;
        }finally {
            spider.close();
        }

    }

    @RequestMapping("hello")
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("msg", "测试");
        return modelAndView;
    }

}
