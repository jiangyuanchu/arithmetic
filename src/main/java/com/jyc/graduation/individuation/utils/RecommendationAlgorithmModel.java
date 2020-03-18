package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.individuation.domain.WordInfo;

import java.util.Arrays;
import java.util.List;

public class RecommendationAlgorithmModel {

    //设置热度衰减周期
    private static int TIME_OUT_DAY = 30;

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
            System.out.println("getWordAnalysisArraySort======== : " + timeDifference);
            System.out.println("getWordAnalysisArraySort======== : " + word.getWord());
            word.setRecommendationRate(RecommendationAlgorithmModel.getTimeWeightNumber(timeDifference));
        }

        Arrays.sort(wordInfos);

        return wordInfos;
    }

}
