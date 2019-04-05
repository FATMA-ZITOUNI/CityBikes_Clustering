# CityBikes Clustering
 

## Description:
The project is a clustering analytics application that uses Kmeans method to create clusters based on the location of the bike stations.\ 
This application is build in he interface intelliJ using sbt and scala


## Requirements:
- JDB 8.1\
- Scala 2.11.12\
- SBT 1.2.8

## Steps:
  file *Build.SBT*: \
1- import Dependencies

  file *run.Scala*:\
1- Create SparkSession\
2- Import data (json file)\
3- Assemle features\
4- Create KMeans Model\
5- Apply the model on the data\
6- Export the result in csv file
