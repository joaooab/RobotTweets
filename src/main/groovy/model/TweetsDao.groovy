package model

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import factory.MongoDBFactory

class TweetsDao {

    static DBCollection collection = MongoDBFactory.instance

    static void salvar(Map<String, Object> parametros) {
        BasicDBObject objetoDao = new BasicDBObject()
        objetoDao.put("tweet_ID", parametros.tweet_ID)
        objetoDao.put("user", parametros.user)
        objetoDao.put("text", parametros.text)
        objetoDao.put("url", parametros.URLEntities)
        objetoDao.put("hashtag", parametros.hashtag)
        objetoDao.put("date", parametros.date)

        try {
            collection.insert(objetoDao)
            printaLogs(parametros)
        } catch (Exception e) {
            println("Erro: conexão ${e.getMessage()}")
        }
    }

    static void printaLogs(Map<String, Object> parametros) {
        println("@ ${parametros.user}: ${parametros.text}")
    }

}
