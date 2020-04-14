package com.jyc.graduation.networkdisk.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class NetworkDiskData {

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的appKey和openId
    public static final String APP_KEY ="J90MchkBF800";
    public static final String OPEN_ID ="gQtRdhQd1wUYJp41";

    //将map型转为请求参数型
    public static String urlEncode(Map<String,Object> params) {

        if(params==null){return "";};

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,Object> i : params.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String r=sb.toString();
        if(r.endsWith("&")){
            r = r.substring(0,r.length()-1);
        }
        return r;
    }

    /**
     *
     * @param requestUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 请求结果
     * @throws Exception
     */
    public static String requestContent(String requestUrl, Map<String,Object> params,String method) throws Exception {

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {

            //组装请求链接
            StringBuffer sb = new StringBuffer();

            if(method!=null&&method.equalsIgnoreCase("get")){
                requestUrl = requestUrl+"?"+urlEncode(params);
            }

            //默认get
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if(method!=null&&method.equalsIgnoreCase("post")){
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
            }

            //参数配置
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            if (params!= null && method.equalsIgnoreCase("post")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlEncode(params));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //读取数据
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }


//    public static void main(String[] args) throws Exception{
//
//        String domain="http://api.xiaocongjisuan.com/";
//        String servlet="data/skydriverdata/get";
//        String method="get";
//
//        String requestUrl=domain+servlet;
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("appKey",APP_KEY);
//        params.put("openId",OPEN_ID);
//
//        //变动部分
//        params.put("q","宇智波佐助");
//        params.put("currentPage",1);
//        params.put("pageSize",20);
//
//
//        String result=requestContent(requestUrl,params,method);
//
//        JsonDispose jsonDispose = new JsonDispose();
//        jsonDispose.jsonDispose(result);
//
//    }
}