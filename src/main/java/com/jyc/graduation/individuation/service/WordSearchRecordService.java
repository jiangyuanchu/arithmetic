package com.jyc.graduation.individuation.service;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordInfo;
import com.jyc.graduation.individuation.mapper.WordSearchRecordMapper;
import com.jyc.graduation.individuation.utils.DateUtils;
import com.jyc.graduation.individuation.utils.PropertiesIsOther;
import com.jyc.graduation.individuation.utils.TimeSpringbackMechanism;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class WordSearchRecordService {

    @Autowired
    WordSearchRecordMapper wordSearchRecordMapper;

    @Autowired
    DateUtils dateUtils;

    @Autowired
    TimeSpringbackMechanism timeSpringbackMechanism;

    public void wordSearchRecord(UserHistory userHistory){
        if(userHistory.getType() != null){
            WordInfo wordInfo =  wordSearchRecordMapper.selectType(userHistory);
            wordInfo.setWord(userHistory.getWord());
            wordInfo.setType(userHistory.getType());

            dealToday(wordInfo);

            if(wordInfo.getFrequency() != null){
                wordInfo.setFrequency(wordInfo.getFrequency() + 1);
                wordSearchRecordMapper.updateType(wordInfo);
                log.info("查询词词类更新完成: "+userHistory.getType());
            }else{
                wordInfo.setDate(dateUtils.dateToTimestamp(new Date()));
                wordInfo.setFrequency(1);
                wordSearchRecordMapper.insertType(wordInfo);
                log.info("查询词词类新增记录完成: "+userHistory.getType());
           }
        }

        String properties = userHistory.getProperties();

        if(properties != null){
            if(PropertiesIsOther.isOther(properties)){
                WordInfo wordInfo =  wordSearchRecordMapper.selectOther(userHistory);
                wordInfo.setWord(userHistory.getWord());
                wordInfo.setProperties(userHistory.getProperties());

                dealToday(wordInfo);

                if(wordInfo.getFrequency() != null){
                    wordInfo.setFrequency(wordInfo.getFrequency() + 1);
                    wordSearchRecordMapper.updateOther(wordInfo);
                    log.info("其他词性更新完成");
                }else{
                    wordInfo.setFrequency(1);
                    wordSearchRecordMapper.insertOther(wordInfo);
                    log.info("其他词性新增记录完成");
                }
            }else{
                WordInfo wordInfo =  wordSearchRecordMapper.selectProperties(userHistory);
                wordInfo.setWord(userHistory.getWord());
                wordInfo.setProperties(userHistory.getProperties());

                dealToday(wordInfo);

                if(wordInfo.getFrequency() != null){
                    wordInfo.setFrequency(wordInfo.getFrequency() + 1);
                    wordSearchRecordMapper.updateProperties(wordInfo);
                    log.info("查询词词性更新完成: "+userHistory.getProperties());
                }else{
                    wordInfo.setFrequency(1);
                    wordSearchRecordMapper.insertProperties(wordInfo);
                    log.info("查询词词性新增记录完成: "+userHistory.getProperties());
                }
            }
        }
    }

    public WordInfo dealToday(WordInfo wordInfo){
        if(wordInfo.getToday() == null ||
                !dateUtils.isSameDay(dateUtils.timestampToDate(wordInfo.getToday()), new Date())){
            wordInfo.setToday(dateUtils.dateToTimestamp(new Date()));
            wordInfo.setTodayFrequency(1);
        }else{
            Date date = timeSpringbackMechanism.getSpringbackReceipt(wordInfo.getDate() ,wordInfo.getTodayFrequency());
            if(date != null){
                wordInfo.setDate(dateUtils.dateToTimestamp(date));
                wordInfo.setTodayFrequency(0);
            }
            wordInfo.setTodayFrequency(wordInfo.getTodayFrequency() + 1);
        }
        return wordInfo;
    }

}
