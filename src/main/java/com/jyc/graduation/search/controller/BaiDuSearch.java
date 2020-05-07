package com.jyc.graduation.search.controller;

import com.jyc.graduation.search.domain.SearchResult;
import com.jyc.graduation.search.pageprocessor.BaiduPageProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;

import java.util.List;

@RestController
public class BaiDuSearch {

    @RequestMapping("baiduSearch")
    public List<SearchResult> search(String words){
        Spider spider = Spider.create(new BaiduPageProcessor()).thread(5);
        try{
            //获取这个链接下的数据
            String urlTemplate = "http://baidu.com/s?wd=%s&rsv_spt=1&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8";
            //传入搜索关键字进行爬虫抓取
            ResultItems resultItems = spider.<ResultItems>get(String.format(urlTemplate, words));

            List<SearchResult> list = resultItems.get("result");

            return list;
        }finally {
            spider.close();
        }

    }

}
