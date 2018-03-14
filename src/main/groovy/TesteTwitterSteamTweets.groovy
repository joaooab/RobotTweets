import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import model.ConectaMongoFactory
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder
import utils.CredencialsFactory

class TestsTwitterSteamTweets {

    static private ConfigurationBuilder cb = CredencialsFactory.getInstance()
    static private DBCollection collection = ConectaMongoFactory.getInstance()

   static private capturaTweets() {
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance()
        StatusListener statusListener = new StatusListener() {
            @Override
            void onStatus(Status status) {
                BasicDBObject obj = new BasicDBObject()
                obj.put("tweet_ID", status.getId())
                obj.put("usuario", status.getUser().getScreenName())
                obj.put("tweet", status.getText())
                try {
                    println(obj)
                    collection.insert(obj)
                } catch (Exception e) {
                    println("Erro: conexão ${e.getMessage()}")
                }
            }
            @Override
            void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            @Override
            void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            void onStallWarning(StallWarning warning) {
            }

            @Override
            void onException(Exception ex) {
            }
        }
        String[] keyword = {"temer"}
        FilterQuery filterQuery = new FilterQuery()
        filterQuery.track(keyword)
        twitterStream.addListener(statusListener)
        twitterStream.filter(filterQuery)
    }

    static void main(String[] args) {
        printaLogs()
        capturaTweets()
    }

    static void printaLogs() {
        Calendar calendar = Calendar.getInstance()
        calendar.getTime()
        println("[${calendar.getTime()}] DB: ${collection.getDB()} - Collection: ${collection.getCollection()}")
    }
}
