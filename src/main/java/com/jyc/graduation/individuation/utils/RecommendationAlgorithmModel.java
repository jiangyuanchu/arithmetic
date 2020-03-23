package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.config.GlobalVariable;
import com.jyc.graduation.individuation.domain.WordInfo;

import java.util.Arrays;
import java.util.List;

public class RecommendationAlgorithmModel {

    private static int TIME_OUT_DAY = GlobalVariable.TIME_OUT_DAY;

    public static Double getTimeWeightNumber(Double timeDifference){

        if(timeDifference > 0 && timeDifference <= TIME_OUT_DAY){
            double weightNumber = -(1.0/(TIME_OUT_DAY * 2.0)) * (timeDifference * 1.0) + 3.5 * 1.0;
            return weightNumber;
        }else if(timeDifference > TIME_OUT_DAY && timeDifference <= (TIME_OUT_DAY * 2)){
            double weightNumber = -(1.0/(TIME_OUT_DAY / 2.0)) * (timeDifference * 1.0) + 5.0 * 1.0;
            return weightNumber;
        }else if(timeDifference > (TIME_OUT_DAY * 2)){
            double weightNumber = (TIME_OUT_DAY * 2) / (timeDifference * 1.0);
            return weightNumber;
        }else{
            return 0.0;
        }

    }

    public static WordInfo[] getWordAnalysisArraySort(List<WordInfo> list){

        WordInfo[] wordInfos = list.toArray(new WordInfo[list.size()]);

        for (WordInfo word : wordInfos) {
            Double timeDifference = TimeDifference.getDayTimeDifferenceDistanceNow(word.getDate());
            word.setRecommendationRate(RecommendationAlgorithmModel.getTimeWeightNumber(timeDifference));
            System.out.println("推荐权重 ==== " + RecommendationAlgorithmModel.getTimeWeightNumber(timeDifference));
            System.out.println("推荐词 ======== : " + word.getWord());
        }

        Arrays.sort(wordInfos);

        return wordInfos;
    }

}
