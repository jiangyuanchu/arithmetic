package com.jyc.graduation.individuation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserHistory {

    private Integer id;

    private Integer userId;

    private String word;

    private String properties;

    private String type;

    private Date date;

    private Integer frequency;
}
