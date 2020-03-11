package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.individuation.domain.WordAnalysis;
import com.jyc.graduation.individuation.enumeration.PropertiesEnum;

public class WordPropertiesFiltrate {
    public static WordAnalysis propertiesFiltrate(WordAnalysis wordAnalysis){

        String[] wordProperties = wordAnalysis.getWordProperties();

        for (String properties : wordProperties) {
            if(PropertiesEnum.nw.toString().equals(properties)){
                wordAnalysis.setProperties(properties);
                break;
            }else if(PropertiesEnum.nz.toString().equals(properties)){
                wordAnalysis.setProperties(properties);
                break;
            }else if(PropertiesEnum.nt.toString().equals(properties)){
                wordAnalysis.setProperties(properties);
                break;
            }else if(PropertiesEnum.ns.toString().equals(properties)){
                wordAnalysis.setProperties(properties);
                break;
            }else if(PropertiesEnum.nr.toString().equals(properties)){
                wordAnalysis.setProperties(properties);
                break;
            }
        }

        if(wordAnalysis.getProperties() == null || wordAnalysis.getProperties().equals("")){
            for (String properties : wordProperties) {
                wordAnalysis.setProperties(properties);
                if(!wordAnalysis.getProperties().equals(null) || wordAnalysis.getProperties().equals("")){
                    break;
                }
            }
        }


        String[] wordType = wordAnalysis.getWordType();

        for (String type : wordType) {
            if(type != null){
                wordAnalysis.setType(type);
            }
        }

        return wordAnalysis;
    }
}
