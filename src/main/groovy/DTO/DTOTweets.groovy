package DTO

import twitter4j.GeoLocation
import twitter4j.URLEntity

class DTOTweets {
    Long tweet_ID
    String user
    String text
//    GeoLocation geoLocation
    String location
//    URLEntity[] url
    String hashtag
    Date date

    DTOTweets(Map<String, Object> parametros) {
//        tweet_ID = (Long) parametros.tweetId
        user = parametros.user
        text = parametros.text.toString().trim()
//        if (parametros.geoLocation){
//            geoLocation = (GeoLocation) parametros.geoLocation
//        } else {
        location = parametros.location
//        }
//        url = (URLEntity[]) parametros.url
//        hashtag = parametros.hashtag
//        date = (Date) parametros.date
    }

    @Override
    String toString() {
        return "${user}: ${text} - Location: ${location}"
    }
}
