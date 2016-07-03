package nosql.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * fuquanemail@gmail.com
 */
public class MongoDBTest {
    static String host = "192.168.1.128";
    static Integer port = 27017;

    MongoDatabase database = null;

    MongoCollection<Document> collection = null;

    @Test
    public void testConnectionDB() {

        //The MongoClient instance actually represents a pool of connections to the database;
        // you will only need one instance of class MongoClient even with multiple threads.

        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(host, port);

        // 连接到 mongodb 服务
        MongoDatabase database = mongoClient.getDatabase("test");
        System.out.println("database:" + database.getName());


        // To directly connect to a single MongoDB server
        // (this will not auto-discover the primary even if it's a member of a replica set)
        mongoClient = new MongoClient();

        // or
        mongoClient = new MongoClient("localhost");

        // or
        mongoClient = new MongoClient("localhost", 27017);

        // or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
        mongoClient = new MongoClient(
                Arrays.asList(new ServerAddress("localhost", 27017),
                        new ServerAddress("localhost", 27018),
                        new ServerAddress("localhost", 27019)));

        // or use a connection string
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
        mongoClient = new MongoClient(connectionString);

        database = mongoClient.getDatabase("mydb");

    }

    @Test
    public void testGetConnection() {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(host, port);

        // 连接到 mongodb 服务
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("col");
        System.out.println(collection);

        System.out.println(collection.count());
    }

    @Test
    public void createDocument() {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(host, port);

        // 连接到 mongodb 服务
        MongoDatabase database = mongoClient.getDatabase("test");

        // 创建集合
        database.createCollection("c1");

        MongoCollection<Document> collection = database.getCollection("c1");

        System.out.println(collection.count());
    }

    @Test
    public void insertDocument() {
        connection4Test();

        // To create the document using the Java driver, use the Document class.
        // You can use this class to create the embedded document as well.
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));

        MongoCollection<Document> collection = database.getCollection("col");

        collection.insertOne(doc);

        // Add Multiple Documents

        List<Document> documentList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            doc = new Document("name", "MongoDB " + i)
                    .append("type", "database")
                    .append("count", i)
                    .append("info", new Document("x", i).append("y", 100));

            documentList.add(doc);
        }

        collection.insertMany(documentList);

    }

    @Test
    public void testFind() {
        connection4Test();

        MongoCollection<Document> collection = database.getCollection("col");

        // find first one

        Document document = collection.find().first();

        System.err.println(document.toJson());

        // find all
        FindIterable<Document> iterable = collection.find();

        printQuery(iterable);

    }

    @Test
    public void testQueryFilter() {
        connection4Test();

        MongoCollection<Document> collection = database.getCollection("col");

        // We can create a filter to pass to the find() method to get a subset of the documents in our collection

        //  title = "MongoDB 教程"
        FindIterable<Document> iterable = collection.find(Filters.eq("title", "MongoDB 教程"));

        printQuery(iterable);

        // We can use the query to get a set of documents from our collection.

        // now use a range query to get a larger subset
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.err.println(document.toJson());
            }
        };

        collection.find(Filters.eq("title", "MongoDB 教程")).forEach(printBlock);

    }

    @Test
    public void testSort() {
        // We can also use the Sorts helpers to sort documents.
        // We add a sort to a find query by calling the sort() method on a FindIterable.
        // Below we use the exists() helper and sort descending("i") helper to sort our documents:

        connection4Test();

        Document document = collection.find(Filters.exists("count")).sort(Sorts.descending("count")).first();

        System.err.println(document.toJson());
    }

    @Test
    public void updateDocument() {

        connection4Test();

        // update one
        UpdateResult result = collection.updateOne(Filters.eq("count", 10), new Document("$set", new Document("i", 110)));

        System.err.println(result.getModifiedCount());


        // update Many

        //更新文档   将文档中likes=100的文档修改为likes=200

        result = collection.updateMany(Filters.eq("count", 50), new Document("$set", new Document("count", 100)));

        // The update methods return an UpdateResult which provides information
        // about the operation including the number of documents modified by the update.

        System.err.println(result.getModifiedCount());
    }

    @Test
    public void testDeleteDocument() {
        connection4Test();


        // delete one
        DeleteResult result = collection.deleteOne(Filters.eq("count", 110));

        System.out.println(result.getDeletedCount());

        // delete many

        result = collection.deleteMany(Filters.gt("count", 20));

        //The delete methods return a DeleteResult which provides information about the operation including the number of documents deleted.

        System.out.println(result.getDeletedCount());

    }

    @Test
    public void testBulkOperations() {

        connection4Test();

        // bulk operations（大量操作） 是 2.6以后新出现的命令，分为有序的 和 无序的。

        // Ordered bulk operations.
        //        Executes all the operation in order and error out on the first write error. Unordered bulk operations.

        // 2. Ordered bulk operation - order is guarenteed

        List list =  Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                        new InsertOneModel<>(new Document("_id", 5)),
                        new InsertOneModel<>(new Document("_id", 6)),
                        new UpdateOneModel<>(new Document("_id", 1),
                                new Document("$set", new Document("x", 2))),
                        new DeleteOneModel<>(new Document("_id", 2)),
                        new ReplaceOneModel<>(new Document("_id", 3),
                                new Document("_id", 3).append("x", 4)));

        BulkWriteResult result = collection.bulkWrite(list);

        System.out.println(result.getInsertedCount());


        //Executes all the operations and reports any the errors.
        //        Unordered bulk operations do not guarantee order of execution.

        list = Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                new InsertOneModel<>(new Document("_id", 5)),
                new InsertOneModel<>(new Document("_id", 6)),
                new UpdateOneModel<>(new Document("_id", 1),
                        new Document("$set", new Document("x", 2))),
                new DeleteOneModel<>(new Document("_id", 2)),
                new ReplaceOneModel<>(new Document("_id", 3),
                        new Document("_id", 3).append("x", 4)));

        result = collection.bulkWrite(list, new BulkWriteOptions().ordered(false));
        System.out.println(result.getInsertedCount());

    }

    public void printQuery(FindIterable<Document> iterable) {
        MongoCursor<Document> cursor = null;
        try {

            // 过游标遍历检索出的文档集合
            cursor = iterable.iterator();

            while (cursor.hasNext()) {
                System.err.println(cursor.next().toJson());
            }

        } catch (Exception e) {

        } finally {
            //　关闭　cursor
            cursor.close();
        }
    }


    public void connection4Test() {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(host, port);

        // 连接到 mongodb 服务
        database = mongoClient.getDatabase("test");

        collection = database.getCollection("col");
    }

}
