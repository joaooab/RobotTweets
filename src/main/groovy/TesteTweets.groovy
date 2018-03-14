import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import factory.MongoDBFactory
import twitter4j.*
import twitter4j.auth.AccessToken

class TesteTweets {

    static private DBCollection collection = MongoDBFactory.getInstance()

    static void main(String[] args) {
        Twitter twitter = TwitterFactory.getSingleton()
        twitter.setOAuthConsumer("twdP1ydVc4mFV19ZqJHqLOGCl","XbNrPYUC1Y1gcz5alo1ghxX48VWbXPRTMrdtpTAsxd3ahOxtQO")
        AccessToken accessToken = new AccessToken("3019805561-ngPDMljiHeLJSKR3EWxDe7zoBFnhGt8RQWOszoi","ppP7JYB1G7WeGQ7rZC6p9CxNR2hodRyKxJwfqDGWGWnbw")
        twitter.setOAuthAccessToken(accessToken)
        Query query = new Query("temer")
        QueryResult result = twitter.search(query)
        for (Status status : result.getTweets()) {
            BasicDBObject obj = new BasicDBObject()
            obj.put("tweet_ID", status.getId())
            obj.put("usuario", status.getUser().getScreenName())
            obj.put("tweet", status.getText())
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText())
            collection.insert(obj)
        }
    }
}
