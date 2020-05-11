package com.jyc.graduation.search.pageprocessor;

import com.jyc.graduation.search.domain.SearchResult;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class LuoMaPanPageProcessor implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override public void process(Page page) {
        //定义如何抽取页面信息，并保存下来
        //获取百度搜索结果的所有标题和超链接
        List<Selectable> selectables = page.getHtml().xpath("//div[@class='roma-it']/div[@class='roma-info']").nodes();
        List<SearchResult> list = new ArrayList<>();
        for (Selectable selectable:selectables){
            SearchResult searchResult = new SearchResult();
            searchResult.setDescription(selectable.xpath("//h1[@class='roma-title']/a/allText()").toString());
            searchResult.setUrl(selectable.xpath("//h1[@class='roma-title']/a/@href").toString());
            list.add(searchResult);
        }
        page.putField("result",list);
    }

    @Override public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new LuoMaPanPageProcessor()).thread(5);
        try{
            //获取这个链接下的数据
            String urlTemplate = "https://www.luomapan.com/search?keyword=%s";
            //传入搜索关键字进行爬虫抓取
            ResultItems resultItems = spider.<ResultItems>get(String.format(urlTemplate, "淘宝"));

            List<SearchResult> list = resultItems.get("result");

            System.out.println(list);
        }finally {
            spider.close();
        }
    }
}
