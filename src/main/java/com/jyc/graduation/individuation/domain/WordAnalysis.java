package com.jyc.graduation.individuation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WordAnalysis {

    private String[] wordProperties;

    private String[] wordType;

    private String properties;

    private String type;
}
