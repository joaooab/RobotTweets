import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder

class CapturaTweets {

    private ConfigurationBuilder cb
    private DBCollection collection = ConectaMongoFactory.getInstance()

    private capturaTweets() {
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance()
        StatusListener statusListener = new StatusListener() {
            @Override
            void onStatus(Status status) {
                BasicDBObject obj = new BasicDBObject()
                obj.put("tweet_ID", status.getId())
                obj.put("usuario", status.getUser().getScreenName())
                obj.put("tweet", status.getText())
                try {
                    collection.insert(obj)
                } catch (Exception e) {
                    println("Erro: conexão ${e.getMessage()}")
                }
            }
            //TODO tirar os prints
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
        String[] keyword = { "BolsonaroPresidente" }
        FilterQuery filterQuery = new FilterQuery()
        filterQuery.track(keyword)
        twitterStream.addListener(statusListener)
        twitterStream.filter(filterQuery)
    }

    void configuraCredenciais() {
        cb = new ConfigurationBuilder()
        cb.setDebugEnabled(true)
        cb.setOAuthConsumerKey("twdP1ydVc4mFV19ZqJHqLOGCl")
        cb.setOAuthConsumerSecret("XbNrPYUC1Y1gcz5alo1ghxX48VWbXPRTMrdtpTAsxd3ahOxtQO")
        cb.setOAuthAccessToken("3019805561-ngPDMljiHeLJSKR3EWxDe7zoBFnhGt8RQWOszoi")
        cb.setOAuthAccessTokenSecret("ppP7JYB1G7WeGQ7rZC6p9CxNR2hodRyKxJwfqDGWGWnbw")

    }

    static void main(String[] args) {
        CapturaTweets capturaTweets = new CapturaTweets()
        capturaTweets.configuraCredenciais()
        capturaTweets.capturaTweets()
    }

}
