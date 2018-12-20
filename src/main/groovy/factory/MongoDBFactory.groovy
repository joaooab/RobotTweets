package factory

import com.mongodb.*

class MongoDBFactory {

    private static DB banco
    private static DBCollection collection

    static DBCollection getInstance() {
        try {
            Mongo mongoCliente = new MongoClient("127.0.0.1")
            banco = mongoCliente.getDB("twDB")

            collection = banco.getCollection("tweets_pos_eleicao")
            BasicDBObject index = new BasicDBObject("tweet_ID", 1)
            collection.ensureIndex(index, new BasicDBObject("unique", true))

            printaLogs()
            return collection
        } catch (UnknownHostException | MongoException ex) {
            System.out.println("MongoException :" + ex.getMessage())
            return null
        }
    }

    static void printaLogs() {
        Calendar calendar = Calendar.getInstance()
        println("[${calendar.getTime()}] DB: ${collection.getDB()} - Collection: ${collection.getCollection()}")
    }
}
