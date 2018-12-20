package model

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import factory.MongoDBFactory

class Tweets {

    static DBCollection collection = MongoDBFactory.instance

    static void salvar(Map<String, Object> parametros) {
        BasicDBObject objetoDao = new BasicDBObject()
        objetoDao.put("tweet_ID", parametros.tweet_ID)
//        objetoDao.put("user", parametros.user)
        objetoDao.put("text", parametros.text)
//        objetoDao.put("location", parametros.location)
//        objetoDao.put("url", parametros.URLEntities)
//        objetoDao.put("hashtag", parametros.hashtag)
//        objetoDao.put("date", parametros.date)

        try {
            collection.insert(objetoDao)
        } catch (Exception e) {
            println("Erro: conexão ${e.getMessage()}")
        }
    }

}
