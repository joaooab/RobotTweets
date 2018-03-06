import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.MongoClient
import spock.lang.Specification

class ConectaMongoFactoryTest extends Specification {

    def testaConexaoComBanco(){

        given: "Instancia database"
            MongoClient mongoClient = new MongoClient("localhost")
            DB db = mongoClient.getDB("twDBTest")

        when: "Insere um documento"
            DBCollection collection = db.getCollection("test")
            BasicDBObject documento = new BasicDBObject("name", "MongoDB").append("type", "database")
                    .append("count", 1)
                    .append("info", new BasicDBObject("x", 203).append("y", 102))
            collection.insert(documento)

        then: "Confere se o documento foi inserido"
            DBObject myDoc = collection.findOne()
            println(myDoc)
            collection.count() == 1
            collection.remove(myDoc)
            println("Documento Removido")
    }
}
