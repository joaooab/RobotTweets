import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoException

class ConectaMongoFactory {

    private static DB banco
    private static DBCollection collection

    static DBCollection getInstance() {
        try {
            Mongo mongoCliente = new MongoClient("127.0.0.1")
            banco = mongoCliente.getDB("twDB")
            collection = banco.getCollection("tweets")
            BasicDBObject index = new BasicDBObject("tweet_ID", 1)
            collection.createIndex(index, new BasicDBObject("unique", true))
            return collection
        } catch (UnknownHostException | MongoException ex) {
            System.out.println("MongoException :" + ex.getMessage())
        }
    }
}
