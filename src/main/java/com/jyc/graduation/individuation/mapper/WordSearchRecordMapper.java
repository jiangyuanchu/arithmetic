package com.jyc.graduation.individuation.mapper;

import com.jyc.graduation.individuation.domain.UserHistory;
import com.jyc.graduation.individuation.domain.WordInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WordSearchRecordMapper {

    @Insert("insert into word_${type} (word, frequency ,date ,today_frequency ,today) "
            + "values (#{word} ,#{frequency} ,#{date} ,#{todayFrequency} ,#{today})")
    void insertType(WordInfo wordInfo);

    @Select("select * from word_${type} where word = #{word}")
    WordInfo selectType(UserHistory userHistory);

    @Update("update word_${type} set date = #{date} ,"
            + "frequency = #{frequency} ,today_frequency = #{todayFrequency} ,today = #{today} where word = #{word}")
    Integer updateType(WordInfo wordInfo);

    @Insert("insert into word_${properties} (word, frequency ,date ,today_frequency ,today) "
            + "values (#{word} ,#{frequency} ,#{date} ,#{todayFrequency} ,#{today})")
    void insertProperties(WordInfo wordInfo);

    @Select("select * from word_${properties} where word = #{word}")
    WordInfo selectProperties(UserHistory userHistory);

    @Update("update word_${properties} set date = #{date} ,"
            + "frequency = #{frequency} ,today_frequency = #{todayFrequency} ,today = #{today} where word = #{word}")
    Integer updateProperties(WordInfo wordInfo);

    @Insert("insert into word_other (word, frequency ,date ,today_frequency ,today) "
            + "values (#{word} ,#{frequency} ,#{date} ,#{todayFrequency} ,#{today})")
    void insertOther(WordInfo wordInfo);

    @Select("select * from word_other where word = #{word}")
    WordInfo selectOther(UserHistory userHistory);

    @Update("update word_other set date = #{date} ,"
            + "frequency = #{frequency} ,today_frequency = #{todayFrequency} ,today = #{today} where word = #{word}")
    Integer updateOther(WordInfo wordInfo);

}
