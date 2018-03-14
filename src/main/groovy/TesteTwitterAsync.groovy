import twitter4j.AsyncTwitter
import twitter4j.AsyncTwitterFactory
import twitter4j.Status
import twitter4j.TwitterAdapter
import twitter4j.TwitterException
import twitter4j.TwitterListener

class TesteTwitterAsync {

    public static void main(String[] args) {
        TwitterListener listener = new TwitterAdapter() {
            @Override public void updatedStatus(Status status) {
                System.out.println("Successfully updated the status to [" +
                        status.getText() + "].");
            }
//
//            @Override public void onException(TwitterException e, int method) {
//                if (method == .UPDATE_STATUS) {
//                    e.printStackTrace();
//                } else {
//                    throw new AssertionError("Should not happen");
//                }
//            }
        }
        // The factory instance is re-useable and thread safe.
        AsyncTwitterFactory factory = new AsyncTwitterFactory();
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.updateStatus(args[0]);
    }
}
