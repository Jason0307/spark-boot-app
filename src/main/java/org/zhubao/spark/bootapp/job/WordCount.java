package org.zhubao.spark.bootapp.job;
import java.io.Serializable;
import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import scala.Tuple2;

@Component
public class WordCount implements Serializable {

	private static final long serialVersionUID = 2941761993840352755L;

	@Autowired
    private JavaSparkContext javaSparkContext;

    @Value("${input.file}")
    private String inputFile;

    public void count() {

        JavaRDD<String> tokenized = javaSparkContext.textFile(inputFile).flatMap((s1) -> Arrays.asList(s1.split(" ")));
        
        JavaPairRDD<String, Integer> wordNum = tokenized.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairRDD<String, Integer> wordResult = wordNum.reduceByKey((i1, i2) -> i1 + i2);
        wordResult.collect().forEach(s -> {
        	System.out.println(s);
        	System.out.println(s._1);
        	System.out.println(s._2);
        });
       
    }
}