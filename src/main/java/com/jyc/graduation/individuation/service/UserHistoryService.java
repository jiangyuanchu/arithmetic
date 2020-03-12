package com.jyc.graduation.individuation.service;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.mapper.UserHistoryMapper;
import com.jyc.graduation.individuation.utils.WordProperties;
import com.jyc.graduation.individuation.utils.WordPropertiesFiltrate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserHistoryService {

    @Autowired
    UserHistoryMapper userHistoryMapper;

    @Autowired
    WordSearchRecordService wordSearchRecordService;

    public WordAnalysis searchHistory(UserHistory userHistory){

        WordAnalysis wordAnalysis = null;
        try{
            wordAnalysis = WordProperties.wordProperties(userHistory.getWord());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        WordPropertiesFiltrate.propertiesFiltrate(wordAnalysis);
        userHistory.setProperties(wordAnalysis.getProperties()).setType(wordAnalysis.getType());

        log.info("成功接入百度智能AI，正在写入数据库");
        Date date = new Date();
        userHistory.setDate(date).setFrequency(1);

        Integer frequency = userHistoryMapper.selectFrequencyForID(userHistory);
        if(frequency == null) {
            userHistoryMapper.insert(userHistory);
            log.info("用户首次查询记录成功");
        }else {
            userHistory.setFrequency(frequency + 1);
            userHistoryMapper.updateFrequencyForID(userHistory);
            log.info("用户多次查询更新成功");
        }

        try{
            wordSearchRecordService.wordSearchRecord(userHistory);
        }catch(Exception e){
            e.printStackTrace();
            log.info("查询词记录失败");
        }

        return wordAnalysis;
    }

}
