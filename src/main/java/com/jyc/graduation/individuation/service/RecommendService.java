package com.jyc.graduation.individuation.service;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.domain.WordInfo;
import com.jyc.graduation.individuation.mapper.RecommendMapper;
import com.jyc.graduation.individuation.utils.PropertiesIsOther;
import com.jyc.graduation.individuation.utils.RecommendationAlgorithmModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RecommendService{

    @Autowired
    RecommendMapper recommendMapper;

    public List<WordInfo> getRecommendList(UserHistory userHistory){

        List<UserHistory> list = recommendMapper.selectUserHistory(userHistory);

        //得到用户搜索类型比重
        Map<String, Integer> map = new HashMap<>(list.size());
        for (UserHistory user : list) {
            String properties = user.getProperties();
            String type = user.getType();
            if(properties != null){
                if(PropertiesIsOther.isOther(properties)){
                    properties = "other";
                }
                if (map.containsKey(properties)){
                    map.put(properties ,map.get(properties) + 1);
                }else{
                    map.put(properties , 1);
                }
            }
            if(type != null){
                if (map.containsKey(type)){
                    map.put(type ,map.get(type) + 1);
                }else{
                    map.put(type , 1);
                }
            }
        }
        log.info("得到用户搜索类型比重");

        Set<String> mapKey = map.keySet();
        List<WordInfo> recommendList = new ArrayList<>();
        for (String key : mapKey) {
            WordAnalysis wordAnalysis = null;
            wordAnalysis = WordAnalysis.builder().properties(key).build();
            List<WordInfo> wordInfoList = recommendMapper.selectRecommendListForProperties(wordAnalysis);
            WordInfo[] wordInfos = RecommendationAlgorithmModel.getWordAnalysisArraySort(wordInfoList);
//            System.out.println("长度"+wordInfos.length);
//            for(WordInfo wordInfo : wordInfos){
//                System.out.println("输出：" + wordInfo);
//            }
            //按比重装载数据
            for (int i=0 ;i<map.get(key) ;i++){
                if(i >= wordInfos.length){
                    break;
                }
                recommendList.add(wordInfos[i]);
            }
        }
        log.info("装载成功，返回推荐列表");

        return recommendList;
    }


}
