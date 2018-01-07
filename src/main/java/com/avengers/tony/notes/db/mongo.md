# Mongo
MongoDB is an open-source database developed by MongoDB, Inc.<br>
MongoDB stores data in JSON-like documents that can vary in structure. <br>
This data model give you the ability to represent hierarchical relationships, to store arrays, and other more complex structures easily. <br>
It is definitely one of the most popular NoSQL databases as of now.

## The Good

* Flexible Data Model

In today's dynamic use cases and ever-changing applications, having a flexible data model is a boon. <br>
A flexible data model means that there is no predefined schema, and the document can hold any set of values based on any key.

* Expressive Query Syntax

The query language of MongoDB is very expressive and is easy to understand. 

* Performance

Query performance is one of the strong points of MongoDB. <br>
It stores most of the workable data in RAM. <br>
All data is persisted in the hard disk, but during a query, it does not fetch the data from the hard disk. It gets it from the local RAM and, hence, is able to serve much faster. <br>
Here, it is important to have the right indexes and enough RAM to benefit from MongoDB's performance.<br>

* Mutiple storage engine

**WiredTiger** is the default storage engine starting in MongoDB 3.2. <br>
It is well-suited for most workloads and is recommended for new deployments.<br>
 WiredTiger provides a document-level concurrency model, checkpointing, and compression, among other features. <br>
 In MongoDB Enterprise, WiredTiger also supports Encryption at Rest.

**MMAPv1** is the original MongoDB storage engine and is the default storage engine for MongoDB versions before 3.2. <br>
It performs well on workloads with high volumes of reads and writes, as well as in-place updates.

**In-Memory Storage Engine** is available in MongoDB Enterprise. <br>
Rather than storing documents on-disk, it retains them in-memory for more predictable data latencies.

* Scalable and Reliable

MongoDB is highly scalable, using shards. <br>
Horizontal scalability is a big plus in most NoSQL databases. MongoDB is no exception.<br>
It is also highly reliable due to its replica sets, and the data is replicated in more nodes asynchronously.

* Async Drivers

Nonblocking IO using async drivers are essential in all modern applications that are built for speed. <br>
MongoDB has async driver support for most of the popular languages.

* Documents = Objects
The good thing about having a document database is that your object can directly be stored as a single document in MongoDB. There is no need of an ORM here.

## The Bad

* Transactions

If you need to update more than one document or collection per user request, don't use MongoDB. <br>
It may lead to corrupted data, as there is no ACID guarantee. <br>
Rollbacks have to be handled by your application.<br>

* No Triggers

In RDBMSs, we have the luxury of triggers, which have saved us in many cases. <br>
This luxury is missing in MongoDB.

* Storage

MongoDB needs more storage than other popular databases. <br>
The introduction of WiredTiger in MongoDB 3.0 has solved the storage issue, but using WiredTiger may not be ideal for most of the applications.

* Disk Cleanup

MongoDB does not automatically clean up the disk space. <br>
So if the documents are rewritten or deleted, the disk space is not released. <br>
This happens during restart or has to be done manually.

## The Ugly

* Hierarchy of Self

If you have a data model where an object can have recursive children, the MongoDB document can become very ugly. <br>
Indexing, searching, and sorting these recursive embedded documents can be very hard.

* Joins

Joining two documents is also not simple in MongoDB. <br>
Though MongoDB 3.2 supports left outer joins (lookup), it is not yet mature. <br>
If your applications require pulling data from multiple collections in a single query, it might not be possible. <br>
Hence you have to make multiple queries, which might make your code look a bit messy.

* Indexing

Though speed is advertised as a big plus of MongoDB, it is achievable only if you have the right indexes. <br>
If you end up having poorly implemented indexes or composite indexes in an incorrect order, MongoDB can be one of the slowest databases.<br>
If you have a lot of filter by and sort by fields, you may end up having a lot of indexes on a collection, which, of course, is not good.

* Duplicate Data

You may end up having a lot of duplicate data, as MongoDB does not support well-defined relationships.<br>
Updating this duplicate data can be hard and, also due to lack of ACID compliance, we might end up having corrupted data.


## MongoDB Best Practices

* Hardware
Ensure your working set fits in RAM.<br>
Use compression.<br>
Run a single MongoDB per server.<br>
Use SSDs for write-heavy applications.

* Data Model

Store all data for a record in a single document.<br>
Avoid large documents.<br>
Avoid unnecessarily long field names.<br>
Eliminate unnecessary indexes.<br>
Remove indexes that are prefixes of other indexes.<br>

* Application
Update only modified fields.<br>
Avoid negation in queries.<br>
Run explain() for every complex query.<br>
Use covered queries when possible.<br>
Use bulk inserts when needed.<br>

* Setup and Configuration.

Have at least one secondary and one arbiter.<br>
Set write concern to 2 when the data is critical.<br>
Havea  daily dump of data for backup.


*Repost from [Darel Lasrado' artical](https://dzone.com/articles/mongodb-the-good-the-bad-and-the-ugly)*



# Usage in java

## Config

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import java.net.UnknownHostException;
import com.mongodb.MongoClient;

@Configuration
@PropertySource(value = "classpath:github.enterprise.properties")
@ComponentScan(basePackages = "com.ebay.ecg.testcase.core")
public class CoreConfig {
    @Bean
    public MongoClient MongoClient(Environment environment) throws UnknownHostException {
        final String mongoUrl = environment.getProperty("mongo.url");
        final String mongoPort = environment.getProperty("mongo.port");
        MongoClient mongoClient = new MongoClient(mongoUrl, Integer.parseInt(mongoPort));
        return mongoClient;
    }
}
```

## Resources

```bash
#github.enterprise.properties
mongo.url=127.0.0.1
mongo.port=27017
```

## Import

```java
private MongoClient mongoClient;

@Autowired
public mongoServiceImpl(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
}

DB db = mongoClient.getDB(dbName);
DBCollection collection = db.getCollection(collectionName);
```

## Query

```java
//get first document
DBObject doc = collection.findOne();

//get all documents
DBCursor cursor = collection.find();
while(cursor.hasNext()) ==> cursorGetter();
{ System.out.println(cursor.next()); }

//get exactly one
BasicDBObject Query = new BasicDBObject();
Query.put("number", 5);
DBCursor cursor = collection.find(Query);

//number in 2, 4 and 5
BasicDBObject Query = new BasicDBObject();
List<Integer> list = new ArrayList<Integer>();
list.add(2);
list.add(4);
list.add(5);
Query.put("number", new BasicDBObject("$in", list));
DBCursor cursor = collection.find(Query);

//5 > number > 2
BasicDBObject Query = new BasicDBObject();
Query.put("number", new BasicDBObject("$gt", 2).append("$lt", 5));
DBCursor cursor = collection.find(Query);

//number != 4
BasicDBObject Query = new BasicDBObject();
Query.put("number", new BasicDBObject("$ne", 4));
DBCursor cursor = collection.find(Query);

//number = 2 and name = 'mkyong-2'
BasicDBObject Query = new BasicDBObject();
List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
obj.add(new BasicDBObject("number", 2));
obj.add(new BasicDBObject("name", "mkyong-2"));
Query.put("$and", obj);
DBCursor cursor = collection.find(Query);

//name like pattern 'Mky.*-[1-3]', case insensitive
BasicDBObject Query = new BasicDBObject();
Query.put("name",new BasicDBObject("$regex", "Mky.*-[1-3]").append("$options", "i"));
DBCursor cursor = collection.find(Query);
```

## Insert

```json
{
    "database" : "mkyongDB",
    "table" : "hosting",
    "detail" :{
        records : 99,
        index : "vps_index1",
        active : "true"
    }
}
```
```java
//use BasicDBObject
BasicDBObject document = new BasicDBObject();
document.put("database", "mkyongDB");
document.put("table", "hosting");

BasicDBObject documentDetail = new BasicDBObject();
documentDetail.put("records", 99);
documentDetail.put("index", "vps_index1");
documentDetail.put("active", "true");

document.put("detail", documentDetail);

collection.insert(document);

//use BasicDBObjectBuilder
BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
.add("database", "mkyongDB")
.add("table", "hosting");

BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
.add("records", 99)
.add("index", "vps_index1")
.add("active", "true");

documentBuilder.add("detail", documentBuilderDetail.get());

collection.insert(documentBuilder.get());

//use map
Map<String, Object> documentMap = new HashMap<String, Object>();
documentMap.put("database", "mkyongDB");
documentMap.put("table", "hosting");

Map<String, Object> documentMapDetail = new HashMap<String, Object>();
documentMapDetail.put("records", 99);
documentMapDetail.put("index", "vps_index1");
documentMapDetail.put("active", "true");

documentMap.put("detail", documentMapDetail);

collection.insert(new BasicDBObject(documentMap));

//insert json String
String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
  "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

DBObject dbObject = (DBObject)JSON.parse(json);
collection.insert(dbObject);
```

## Remove

```java
//drop the collection
collection.drop();

//remove all the document
collection.remove(new BasicDBObject());

//for 1,2,3,4,5,6,7,8,9,10
//remove 1
DBObject doc = collection.findOne(); //get first document
collection.remove(doc);

//remove 2
BasicDBObject document = new BasicDBObject();
document.put("number", 2);
collection.remove(document);

//remove 3
collection.remove(new BasicDBObject().append("number", 3));

//remove 4,5
BasicDBObject query2 = new BasicDBObject();
List<Integer> list = new ArrayList<Integer>();
list.add(4);
list.add(5);
query2.put("number", new BasicDBObject("$in", list));
collection.remove(query2);

//remove 10
BasicDBObject query = new BasicDBObject();
query.put("number", new BasicDBObject("$gt", 9));
collection.remove(query);
```

## Update

```json
{ "hosting" : "hostA", "type" : "vps",      "clients" : 1000 },
{ "hosting" : "hostB",  "type" : "server",  "clients" : 100 },
{ "hosting" : "hostC",  "type" : "vps",      "clients" : 900 }
```
```java
//where hosting = ‘hostB’, and update it’s clients values from 100 to 110.
//wrong way
BasicDBObject newDocument = new BasicDBObject();
newDocument.put("clients", 110);
BasicDBObject searchQuery = new BasicDBObject().append("hosting", "hostB");
collection.update(searchQuery, newDocument);
Output :
{ "hosting" : "hostA" , "type" : "vps" , "clients" : 1000}
{ "clients" : 110 }
{ "hosting" : "hostC" , "type" : "vps" , "clients" : 900}

//right way: use "$set"
BasicDBObject newDocument = new BasicDBObject();
newDocument.append("$set", new BasicDBObject().append("clients", 110));
BasicDBObject searchQuery = new BasicDBObject().append("hosting", "hostB");
collection.update(searchQuery, newDocument);
Output :
{ "hosting" : "hostA" , "type" : "vps" , "clients" : 1000}
{ "hosting" : "hostB" , "type" : "server" , "clients" : 110}
{ "hosting" : "hostC" , "type" : "vps" , "clients" : 900}

//use $inc modifier to increase a particular value(100-->199)
BasicDBObject newDocument = new BasicDBObject().append("$inc",new BasicDBObject().append("total clients", 99));
collection.update(new BasicDBObject().append("hosting", "hostB"), newDocument);
Output :
{ "_id" : { "$oid" : "id"} , "hosting" : "hostA" , "type" : "vps" , "clients" : 1000}
{ "_id" : { "$oid" : "id"} , "hosting" : "hostB" , "type" : "dedicated server" , "clients" : 199}
{ "_id" : { "$oid" : "id"} , "hosting" : "hostC" , "type" : "vps" , "clients" : 900}

//update multi obj's value
BasicDBObject updateQuery = new BasicDBObject();
updateQuery.append("$set",new BasicDBObject().append("clients", "888"));
BasicDBObject searchQuery = new BasicDBObject();
searchQuery.append("type", "vps");
collection.updateMulti(searchQuery, updateQuery);

Output :
{ "_id" : { "$oid" : "id"} , "hosting" : "hostA" , "clients" : "888" , "type" : "vps"}
{ "_id" : { "$oid" : "id"} , "hosting" : "hostB" , "type" : "dedicated server" , "clients" : 100}
{ "_id" : { "$oid" : "id"} , "hosting" : "hostC" , "clients" : "888" , "type" : "vps"}
```