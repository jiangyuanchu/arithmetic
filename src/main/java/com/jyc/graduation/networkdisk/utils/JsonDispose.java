package com.jyc.graduation.networkdisk.utils;

import com.alibaba.fastjson.JSON;
import com.jyc.graduation.networkdisk.domain.NetworkAll;
import com.jyc.graduation.networkdisk.domain.NetworkResult;

import java.util.List;

public class JsonDispose {

    public NetworkAll jsonDispose(String str){
        NetworkAll networkAll = JSON.parseObject(str, NetworkAll.class);


        List<NetworkResult> networkResults = networkAll.getData().getResult();

        for (NetworkResult networkResult: networkResults) {
            networkResult.setTitle(networkResult.getTitle().replaceAll("<(\\S*?)[^>]*>.*?|<.*? />", ""));
            networkResult.setContent(networkResult.getContent().replaceAll("<(\\S*?)[^>]*>.*?|<.*? />", ""));
        }
        System.out.println(networkResults.toString());

        return networkAll;
    }

}
