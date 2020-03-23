package com.jyc.graduation.individuation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WordInfo implements Comparable<WordInfo> {

    private String word;

    private Timestamp date;

    private Integer frequency;

    private Double recommendationRate;

    private Integer todayFrequency;

    private Timestamp today;

    private String properties;

    private String type;

    @Override
    public int compareTo(WordInfo o) {
        return (int)(this.recommendationRate - o.recommendationRate);
    }
}
