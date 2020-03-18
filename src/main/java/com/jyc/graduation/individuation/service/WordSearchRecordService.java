package com.jyc.graduation.individuation.service;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.mapper.WordSearchRecordMapper;
import com.jyc.graduation.individuation.utils.PropertiesIsOther;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordSearchRecordService {

    @Autowired
    WordSearchRecordMapper wordSearchRecordMapper;

    public void wordSearchRecord(UserHistory userHistory){
        if(userHistory.getType() != null){
           Integer frequency =  wordSearchRecordMapper.selectType(userHistory);
           if(frequency != null){
               userHistory.setFrequency(frequency + 1);
               wordSearchRecordMapper.updateType(userHistory);
               log.info("查询词词类更新完成: "+userHistory.getType());
           }else{
               userHistory.setFrequency(1);
               wordSearchRecordMapper.insertType(userHistory);
               log.info("查询词词类新增记录完成: "+userHistory.getType());
           }
        }

        String properties = userHistory.getProperties();

        if(properties != null){
            if(PropertiesIsOther.isOther(properties)){
                Integer frequency =  wordSearchRecordMapper.selectOther(userHistory);
                if(frequency != null){
                    userHistory.setFrequency(frequency + 1);
                    wordSearchRecordMapper.updateOther(userHistory);
                    log.info("其他词性更新完成");
                }else{
                    userHistory.setFrequency(1);
                    wordSearchRecordMapper.insertOther(userHistory);
                    log.info("其他词性新增记录完成");
                }
            }else{
                Integer frequency =  wordSearchRecordMapper.selectProperties(userHistory);
                if(frequency != null){
                    userHistory.setFrequency(frequency + 1);
                    wordSearchRecordMapper.updateProperties(userHistory);
                    log.info("查询词词性更新完成: "+userHistory.getProperties());
                }else{
                    userHistory.setFrequency(1);
                    wordSearchRecordMapper.insertProperties(userHistory);
                    log.info("查询词词性新增记录完成: "+userHistory.getProperties());
                }
            }
        }
    }

}
