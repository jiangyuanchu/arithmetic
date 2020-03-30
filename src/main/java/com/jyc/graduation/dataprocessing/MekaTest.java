package com.jyc.graduation.dataprocessing;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.experiment.InstanceQuery;

import java.io.File;
import java.util.Random;

public class MekaTest {
    public static void main(String[] args) throws Exception {
        Classifier m_classifier = new J48();
        MekaTest mekaTest = new MekaTest();
        Instances instances = mekaTest.getData(mekaTest.getQuery(),"SELECT user_id,word,properties FROM user_history");
        Instances instances2 = instances;// mekaTest.getData(mekaTest.getQuery(),"SELECT word FROM word_a");

        instances.setClassIndex(2);

        Evaluation eval = new Evaluation(instances);

        float right = 0.0f;
        instances2.setClassIndex(2);
        m_classifier.buildClassifier(instances2); //训练

        double sum = instances2.numInstances();//测试语料实例数
        for(int  i = 0;i<sum;i++)//测试分类结果

        {

            if(m_classifier.classifyInstance(instances2.instance(i))==instances.instance(i).classValue())//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）

            {

                right++;//正确值加1

            }

//            System.out.println("预测值 == ：" + m_classifier.classifyInstance(instances.instance(i)));
//            System.out.println("真实值 == ：" + instances.instance(i).classValue());

        }
        System.out.println("J48 classification precision:"+(right/sum) +"****"+ m_classifier);
        System.out.println(m_classifier.classifyInstance(instances.instance(2)));

        System.out.println(instances.toString());
        Random rand = new Random(1);
        StringBuffer forPredictionsPrinting = new StringBuffer();
        Boolean outputDistribution = new Boolean(true);
        weka.core.Range attsToOutput = null;
        int folds = 10;
        eval.crossValidateModel(m_classifier, instances2, folds, rand, forPredictionsPrinting ,attsToOutput, outputDistribution);
        System.out.println(eval.toSummaryString("Evaluation results:\n",
                false));
        System.out.println("Correct % = " + eval.pctCorrect());
        System.out.println("Incorrect % = " + eval.pctIncorrect());
        System.out.println("AUC = " + eval.areaUnderROC(1));
        System.out.println("kappa = " + eval.kappa());
        System.out.println("MAE = " + eval.meanAbsoluteError());
        System.out.println("RMSE = " + eval.rootMeanSquaredError());
        System.out.println("RAE = " + eval.relativeAbsoluteError());
        System.out.println("RRSE = " + eval.rootRelativeSquaredError());
        System.out.println("Precision = " + eval.precision(1));
        System.out.println("Recall = " + eval.recall(1));
        System.out.println("fMeasure = " + eval.fMeasure(1));
        System.out.println("Error Rate = " + eval.errorRate());

        System.out.println(eval
                .toMatrixString("=== Overall Confusion Matrix ===\n"));

        System.out.println(forPredictionsPrinting);
    }

    public InstanceQuery getQuery() {
        InstanceQuery query = null;
        try {
            query = new InstanceQuery();
            query.setDatabaseURL("jdbc:mysql://localhost:3306/graduation_project?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=UTC");
            query.setUsername("root");
            query.setPassword("root");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return query;
    }

    /**
     * 查询器根据sql查找
     *
     * @param query
     * @param sql
     * @return
     */
    public Instances getData(InstanceQuery query, String sql) {
        Instances data = null;
        query.setQuery(sql);
        try {
            //将查询的数据反转成数据实例的样式
            data = query.retrieveInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
