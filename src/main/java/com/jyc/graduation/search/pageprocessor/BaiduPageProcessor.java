package com.jyc.graduation.search.pageprocessor;

import com.jyc.graduation.search.domain.SearchResult;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class BaiduPageProcessor implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    //定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override  public void process(Page page) {
        //定义如何抽取页面信息，并保存下来
        //获取百度搜索结果的所有标题和超链接
        List<Selectable> selectables = page.getHtml().xpath("//div[@id='content_left']/div[@class='result c-container']").nodes();
        List<SearchResult> list = new ArrayList<>();
        for (Selectable selectable:selectables){
//            String description = selectable.xpath("//h3[@class='t']/a/allText()").toString();
//            String url = selectable.xpath("//h3[@class='t']/a/@href").toString();
            SearchResult searchResult = new SearchResult();
            searchResult.setDescription(selectable.xpath("//h3[@class='t']/a/allText()").toString());
            searchResult.setUrl(selectable.xpath("//h3[@class='t']/a/@href").toString());
            list.add(searchResult);
        }
        page.putField("result",list);

    }

    @Override public Site getSite() {
        return site;
    }
}
