package com.jyc.graduation.networkdisk.controller;

import com.jyc.graduation.networkdisk.domain.NetworkAll;
import com.jyc.graduation.networkdisk.utils.JsonDispose;
import com.jyc.graduation.networkdisk.utils.NetworkDiskData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class SearchDiskController {

    @Autowired
    NetworkDiskData networkDiskData;

    @RequestMapping("getDiskResource")
    public NetworkAll getDiskResource(String str){

        String domain="http://api.xiaocongjisuan.com/";
        String servlet="data/skydriverdata/get";
        String method="get";

        String requestUrl=domain+servlet;
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("appKey",NetworkDiskData.APP_KEY);
        params.put("openId",NetworkDiskData.OPEN_ID);

        //变动部分
        params.put("q",str);
        params.put("currentPage",1);
        params.put("pageSize",20);

        String result = null;
        try {
            result=networkDiskData.requestContent(requestUrl,params,method);
        }catch (Exception e){
            e.printStackTrace();
            log.info("网盘资源获取错误");
        }

        JsonDispose jsonDispose = new JsonDispose();
        return jsonDispose.jsonDispose(result);

    }

}
