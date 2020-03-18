package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.individuation.enumeration.PropertiesEnum;

public class PropertiesIsOther {

    public static boolean isOther(String properties){
        if(properties.equals(PropertiesEnum.w.toString()) || properties.equals(PropertiesEnum.d.toString())
                || properties.equals(PropertiesEnum.m.toString()) || properties.equals(PropertiesEnum.q.toString())
                || properties.equals(PropertiesEnum.r.toString()) || properties.equals(PropertiesEnum.p.toString())
                || properties.equals(PropertiesEnum.c.toString()) || properties.equals(PropertiesEnum.u.toString())
                || properties.equals(PropertiesEnum.xc.toString()) ){
            return true;
        }else{
            return false;
        }
    }

}
