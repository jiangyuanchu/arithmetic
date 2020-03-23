package com.jyc.graduation.networkdisk.domain;

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
public class NetworkResult {
    private String id;
    private String title;
    private String url;
    private String size;
    private String shareTime;
    private String shareUser;
    private String isDir;
    private String originId;
    private String originName;
    private String categoryId;
    private String categoryName;
    private String password;
    private String acqTime;
    private String content;
    private String uk;
    private String shareId;
    private String fileCount;
    private String fromTable;
    private String uniqueKey;
    private String shortUrl;
    private String extendFiles;
}