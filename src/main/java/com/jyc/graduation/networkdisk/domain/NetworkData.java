package com.jyc.graduation.networkdisk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NetworkData {
    private List<NetworkResult> result;
    private String amount;
    private String totalPage;
    private String time;
    private String currentPage;
}
