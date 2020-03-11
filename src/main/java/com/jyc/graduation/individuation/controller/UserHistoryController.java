package com.jyc.graduation.individuation.controller;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.domain.WordAnalysisAlias;
import com.jyc.graduation.individuation.enumeration.PropertiesEnum;
import com.jyc.graduation.individuation.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserHistoryController {

    @Autowired
    UserHistoryService userHistoryService;

    @RequestMapping("/search")
    public WordAnalysisAlias search(@Valid @RequestBody UserHistory userHistory){

        WordAnalysis wordAnalysis = userHistoryService.searchHistory(userHistory);

        WordAnalysisAlias wordAnalysisAlias = new WordAnalysisAlias();
        if(wordAnalysis.getProperties() != null){
            wordAnalysisAlias.setProperties(PropertiesEnum.valueOf(wordAnalysis.getProperties()).getDisplayName());
        }
        if(wordAnalysis.getType() != null){
            wordAnalysisAlias.setType(PropertiesEnum.valueOf(wordAnalysis.getType()).getDisplayName());
        }

        return  wordAnalysisAlias;
    }
}
