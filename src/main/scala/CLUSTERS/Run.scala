package CLUSTERS
import org.apache.spark.sql.{SparkSession, SaveMode}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.Pipeline

object Run {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder. master("local")
      .appName("BIKES_CLUSTERING")
      .getOrCreate()
    val dataset = spark.read.json("Brisbane_CityBike.json")
    println("Dataset has been imported. It contains:", dataset.count()," entries")
    dataset.show(10)

    val assembler = new VectorAssembler()
      .setInputCols(Array("latitude","longitude"))
      .setOutputCol("features")

    val kmeans = new KMeans()
      .setK(5).setSeed(1L)
      .setFeaturesCol("features")
      .setPredictionCol("cluster")

    val pipeline = new Pipeline().setStages(Array(assembler, kmeans))
    val model = pipeline.fit(dataset)
    model.write.overwrite()//.save("C:/Users/pc/Desktop/BIKES/output/model")

    val clust_res = model.transform(dataset).toDF()
    clust_res.show()

    clust_res.persist().drop("features").repartition(1)
      .write.mode(SaveMode.Overwrite).format("com.databricks.spark.csv")
      .option("header", "true").option("delimiter", ";")
      .save("C:/Users/pc/Desktop/BIKES/output")






  }


}
