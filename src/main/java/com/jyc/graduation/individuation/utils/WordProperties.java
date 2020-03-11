package com.jyc.graduation.individuation.utils;

import com.baidu.aip.nlp.AipNlp;
import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.enumeration.PropertiesEnum;
import org.json.JSONArray;
import org.json.JSONObject;

public class WordProperties {
    //设置APPID/AK/SK
    public static final String APP_ID = "18760322";
    public static final String API_KEY = "sVreOKP315tMGI9USNZKGlYj";
    public static final String SECRET_KEY = "nKCndChZorX6ny871nqHFmLHwcR98XGm";

    public static WordAnalysis wordProperties(String text) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        JSONObject res = client.lexer(text, null);
        System.out.println(res.toString(2));
        JSONArray result =  res.getJSONArray("items");
        Object[] array = new Object[result.length()];
        for (int i=0 ;i<result.length() ;i++){
            array[i] = result.get(i);
        }
        String[] wordProperties = new String[array.length];
        for (int i=0 ;i<array.length ; i++) {
            int a = array[i].toString().indexOf("pos") + 6;
            String s1 = array[i].toString().substring(a);
            String[] s2 = s1.split("\"");
            if (s2[0] == null || s2[0].trim().equals("")){
                continue;
            }
            wordProperties[i] = s2[0];
        }
        String[] wordType = new String[array.length];
        for (int i=0 ;i<array.length ; i++) {
            int a = array[i].toString().indexOf("ne") + 5;
            String s1 = array[i].toString().substring(a);
            String[] s2 = s1.split("\"");
            if (s2[0] == null || s2[0].trim().equals("")){
                continue;
            }
            wordType[i] = s2[0];
        }

        return WordAnalysis.builder().wordProperties(wordProperties).wordType(wordType).build();
    }


    public static void main(String[] args) {

        String string = "海贼王";

        WordAnalysis str = WordProperties.wordProperties(string);

        WordPropertiesFiltrate.propertiesFiltrate(str);

        System.out.println(str.toString());
    }
}