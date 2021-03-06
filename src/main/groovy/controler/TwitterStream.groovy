package controler

import factory.CredentialsFactory
import factory.FilterQueryFactory
import model.Tweets
import twitter4j.*

class TwitterStream {

    static twitter4j.TwitterStream twitterStream = new TwitterStreamFactory().getInstance()

    static void main(String[] args) {
        StatusListener listener = new StatusListener() {
            @Override
            void onStatus(Status status) {
                Map<String, Object> parametros = new HashMap<>()
                if (!status.isRetweet()) {
                    parametros.put("tweet_ID", status.getId())
//                    parametros.put("user", status.getUser().getScreenName())
                    parametros.put("text", status.getText())
//                    parametros.put("location", status.getUser().getLocation())
//                    parametros.put("url", status.getURLEntities())
//                    parametros.put("hashtag", status.getHashtagEntities().toString())
//                    parametros.put("date", status.getCreatedAt())
                    println(parametros)

                    Tweets.salvar(parametros)
                }
            }

            @Override
            void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId())
            }

            @Override
            void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                println("Got track limitation notice:" + numberOfLimitedStatuses)
            }

            @Override
            void onScrubGeo(long userId, long upToStatusId) {
                println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId)
            }

            @Override
            void onStallWarning(StallWarning warning) {
                println("Got stall warning:" + warning)
            }

            @Override
            void onException(Exception ex) {
                ex.printStackTrace()
            }
        }

        CredentialsFactory.getCredencials(twitterStream)
        FilterQuery filterQuery = FilterQueryFactory.getInstance()

        twitterStream.addListener(listener)
        twitterStream.filter(filterQuery)
    }

}
