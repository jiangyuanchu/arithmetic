package com.jyc.graduation.individuation.mapper;

import com.jyc.graduation.individuation.domain.UserHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserHistoryMapper {

    @Insert("insert into user_history (user_id ,word ,properties ,`type` ,date ,frequency) values (#{userId} ,#{word} ,#{properties} ,#{type} ,#{date} ,#{frequency})")
    void insert(UserHistory userHistory);

    @Select("select frequency from user_history where user_id = #{userId} and word = #{word}")
    Integer selectFrequencyForID(UserHistory userHistory);

    @Update("update user_history set frequency = #{frequency} where user_id = #{userId} and word = #{word}")
    Integer updateFrequencyForID(UserHistory userHistory);

}
