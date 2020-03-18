package com.jyc.graduation.individuation.controller;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordInfo;
import com.jyc.graduation.individuation.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @RequestMapping("getRecommendList")
    public List<WordInfo> getRecommendList(@Valid @RequestBody UserHistory userHistory){
        return recommendService.getRecommendList(userHistory);
    }

}
