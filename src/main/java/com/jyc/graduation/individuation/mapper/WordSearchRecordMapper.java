package com.jyc.graduation.individuation.mapper;

import com.jyc.graduation.individuation.domain.UserHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WordSearchRecordMapper {

    @Insert("insert into word_${type} (word, frequency ,date) values (#{word} ,#{frequency} ,#{date})")
    void insertType(UserHistory userHistory);

    @Select("select frequency from word_${type} where word = #{word}")
    Integer selectType(UserHistory userHistory);

    @Update("update word_${type} set frequency = #{frequency} where word = #{word}")
    Integer updateType(UserHistory userHistory);

    @Insert("insert into word_${properties} (word, frequency ,date) values (#{word} ,#{frequency} ,#{date})")
    void insertProperties(UserHistory userHistory);

    @Select("select frequency from word_${properties} where word = #{word}")
    Integer selectProperties(UserHistory userHistory);

    @Update("update word_${properties} set frequency = #{frequency} where word = #{word}")
    Integer updateProperties(UserHistory userHistory);

    @Insert("insert into word_other (word, frequency ,date) values (#{word} ,#{frequency} ,#{date})")
    void insertOther(UserHistory userHistory);

    @Select("select frequency from word_other where word = #{word}")
    Integer selectOther(UserHistory userHistory);

    @Update("update word_other set frequency = #{frequency} where word = #{word}")
    Integer updateOther(UserHistory userHistory);

}
