package utils

import twitter4j.TwitterStream
import twitter4j.auth.AccessToken

class CredencialsFactory {

    static void getCredencials(TwitterStream twitterStream){
        twitterStream.setOAuthConsumer("twdP1ydVc4mFV19ZqJHqLOGCl","XbNrPYUC1Y1gcz5alo1ghxX48VWbXPRTMrdtpTAsxd3ahOxtQO")
        AccessToken accessToken = new AccessToken("3019805561-ngPDMljiHeLJSKR3EWxDe7zoBFnhGt8RQWOszoi","ppP7JYB1G7WeGQ7rZC6p9CxNR2hodRyKxJwfqDGWGWnbw")
        twitterStream.setOAuthAccessToken(accessToken)
    }
}
