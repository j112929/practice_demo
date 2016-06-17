
package com.lxw1234.spark.features
  
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.Word2Vec
  
/**
 * auth: [url=http://lxw1234.com]http://lxw1234.com[/url]
 */
object TestWord2Vec {
  def main(args : Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("lxw1234.com")
    val sc = new SparkContext(conf)
     
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._
     
    val documentDF = sqlContext.createDataFrame(Seq(
      "苹果 官网 苹果 宣布".split(" "),
      "苹果 梨 香蕉".split(" ")
    ).map(Tuple1.apply)).toDF("text")
     
    val word2Vec = new Word2Vec().setInputCol("text").setOutputCol("result").setVectorSize(3).setMinCount(1)
    val model = word2Vec.fit(documentDF)
     
    val result = model.transform(documentDF)
    result.collect().foreach(println)
     
  }
}