package com.jyc.graduation.search.domain;

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
public class SearchResult {

    //搜索结果标题
    private String description;

    //搜索结果链接
    private String url;


}
