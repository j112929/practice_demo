

package com.lxw1234.spark.features.selectors
  
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
  
import org.apache.spark.ml.feature.RFormula
  
/**
 * By  [url=http://lxw1234.com]http://lxw1234.com[/url]
 */
object TestRFormula extends App {
   
    val conf = new SparkConf().setMaster("local").setAppName("lxw1234.com")
    val sc = new SparkContext(conf)
     
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._
     
    //构造数据集
    val dataset = sqlContext.createDataFrame(Seq(
      (7, "US", 18, 1.0),
      (8, "CA", 12, 0.0),
      (9, "NZ", 15, 0.0)
    )).toDF("id", "country", "hour", "clicked")
    dataset.select("id", "country", "hour", "clicked").show()
     
    //当需要通过country和hour来预测clicked时候，
    //构造RFormula，指定Formula表达式为clicked ~ country + hour
    val formula = new RFormula().setFormula("clicked ~ country + hour").setFeaturesCol("features").setLabelCol("label")
    //生成特征向量及label
    val output = formula.fit(dataset).transform(dataset)
    output.select("id", "country", "hour", "clicked", "features", "label").show()
}