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
public class NetworkAll {
    private NetworkData data;
    private String errorMessage;
    private String errorCode;
    private String error;
}
