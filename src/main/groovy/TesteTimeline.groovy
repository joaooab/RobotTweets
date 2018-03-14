import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import utils.CredencialsFactory

class TesteTimeline {

    static void main(String[] args) {
        ConfigurationBuilder cb = CredencialsFactory.getInstance()
        TwitterFactory twitterFactory = new TwitterFactory(cb.build())
        Twitter twitter = twitterFactory.getInstance()
        twitter.getHomeTimeline()
        List<Status> status = twitter.getHomeTimeline()
        for (Status st : status){
            println(st.getUser().getName()+"----------"+st.getText())
        }
    }
}
