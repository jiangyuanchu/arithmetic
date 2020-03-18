package com.jyc.graduation.individuation.mapper;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.domain.WordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecommendMapper {

    @Select("select * from user_history where user_id = #{userId} order by date desc limit 10")
    List<UserHistory> selectUserHistory(UserHistory userHistory);


    @Select("select * from word_${properties} order by frequency desc limit 20")
    List<WordInfo> selectRecommendListForProperties(WordAnalysis wordAnalysis);


}
