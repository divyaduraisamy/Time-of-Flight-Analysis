# Time-Of-Flight-Analysis
  This project is for the coursework "IDS 561 - Big Data Analysis"
 +##Motivation 
  The number of consumer complaints filed with the U.S. Department of Transportation (DOT) has soared by roughly 55 percent from a year ago.Punctuality is one of the key performance indicators in the airline industry and an important service differentiator especially for valuable high-yield customers. In addition , improved on-time performance can help achieve significant high-yield customers. Airlines report delay costs from 0.6 to up to as much as 2.9% of their operating revenues.
  
  There is a huge amount of airline data available at United States Department Of Transportation. The data provides details about the Carrier detail and delay details. This information can be of use to perform analysis and identify the root cause of the delay. Also the result of the analysis can be used to help the travellers to make travel decisions like time of flight etc.Also, Ranking of the airports based on the number of flights arriving/departing provides information about which airport requires more attention. 
 @@ -9,7 +9,7 @@ Apart from this , prediction of the flight delay based on the classification mod
  
   
  
 +##Technical Challenges:
  Massive Data:
  The data set was huge of around 4.5 GB . There were around 4.25 billion records in the table . Loading the data into the database , querying and building a prediction model was very difficult.
  
 @@ -22,30 +22,30 @@ Apart from the huge dataset , the data uploaded in the database consumed more sp
  Setting up AWS Hadoop Instance:
  Though initially the option of AWS EMR instance with inbuilt hadoop configuration was chosen. The EMR costed more money , also there was no option to save the state of instance. Hence the hadoop instance was setup in AWS EC2 instance with creation of master and slave node and configuring these nodes.
  
 -APPROACH:
 -BASIC ANALYSIS USING AGGREGATION QUERIES:
 +##APPROACH:
 +###BASIC ANALYSIS USING AGGREGATION QUERIES:
  Basic Analysis is performed over the data present in the database (MongoDB) using aggregation queries . Aggregation operations group values from multiple documents together, and can perform a variety of operations on the grouped data to return a single result . Some of the analysis performed were
 -States with maximum average delay:
 +####States with maximum average delay:
       An aggregate query was performed to sort the states based on the average delay in all the airports present in the particular state and are sorted in the descending order.
  Query :   db.flights_original.aggregate([   {"$group" : {"_id": "$destStateId",                      "delay" : {$avg : "$arrDelay"}}}         , { "$sort" : {"delay" : -1} }              ]);
 -Day of the year with maximum delay :
 +####Day of the year with maximum delay :
      An aggregate query is run over the data to obtain the day of the year with maximum delay and are sorted in descending order. 
  Query : db.flights_original.aggregate([         {"$group" : {"_id": "$date",                      "delay" : {$avg : "$arrDelay"}}}         , { "$sort" : {"delay" : -1} },{$out:"arrivalcalendar"}]) ;
  
  
 -Carrier with the most departure delay :
 +###Carrier with the most departure delay :
             An aggregate query which obtains the list of carriers with the most departure delay.
    db.flights_original.aggregate({"$group" : {"_id": "$carrier",  "delay" : {$avg :          "$arrDelay"}}} , { "$sort" : {"delay" : -1} }   ])
 -Week of Day with maximum departure delay:
 +####Week of Day with maximum departure delay:
              Aggregate query is used to obtain the result of the day of week with most departure delay. 
  db.flights_original.aggregate([{"$group" : {"_id": {"w" : {"$dayOfWeek" : "$date"}, "h" : {"$hour" : "$date"}}, "delay" : {agg : "$"+aggregateValue}}} , {"$sort": {"delay" : -1}}])
  
 -PAGE RANK ANALYSIS OF AIRPORTS:
 +###PAGE RANK ANALYSIS OF AIRPORTS:
  A graph model is constructed based on the flights arriving to and  departing from airports. The nodes are the Airports , Edges are the flights arriving/departing.The graph model also contains details about the probability that the flight travels from one Node(Airport) to another Node(Airport). Used Mongo DB distributed map reduce framework  to perform page rank analysis on an EC2 cluster.The airports with the most in-links are the influential airports and ranked accordingly using Page Rank algorithm. Since Map Reduce was used , the computation was faster.The formula used for page rank analysis 
  is , 
  PR(A) = (1-d) / N + d (PR(T1)/C(T1) + ... + PR(Tn)/C(Tn))
  
 -PREDICTION MODEL:
 +##PREDICTION MODEL:
  Back-End:
  Data dump from MongoDB is stored in BSON format. Data is fetched from BSON and then curated.  Training data consists of the following fields from the year 2012 to 2013. 
  Origin
