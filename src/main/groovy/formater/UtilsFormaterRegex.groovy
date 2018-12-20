package formater

class UtilsFormaterRegex {

    static String removeTagUsers = '"user":".*?",'
    static String removeTagText = '"text":"'
    static String removeTagId = '\\{"_id":\\{"\\$oid":".+?"\\},'
    static String removeTagTweetId = '"tweet_ID":\\{"\\$numberLong":".*?\\},'
    static String removeTagUrl = '"url":.*?,'
    static String removeTagHashtag = '"hashtag":.*?,'
    static String removeTagDate = '"date":.*?}'
    static String removeTagLocation = '"location":'

    static String removeAllTags(String fileContent) {
        fileContent = removeTagUsers(fileContent)
        fileContent = removeTagText(fileContent)
        fileContent = removeTagId(fileContent)
        fileContent = removeTagTweetId(fileContent)
        fileContent = removeTagUrl(fileContent)
        fileContent = removeTagHashtag(fileContent)
        fileContent = removeTagDate(fileContent)
        fileContent = removeTagLocation(fileContent)
        return  fileContent
    }

    static String formateColumnsAndLines(String fileContent){
        fileContent = formateColumns(fileContent)
        fileContent = formateLines(fileContent)
        return fileContent
    }

    static String removeTagUsers(String fileContent){
         return fileContent.replaceAll(removeTagUsers, '')
    }

    static String removeTagText(String fileContent){
        return fileContent.replaceAll(removeTagText, '')
    }

    static String removeTagId(String fileContent){
        return fileContent.replaceAll(removeTagId, '')
    }

    static String removeTagTweetId(String fileContent){
        return fileContent.replaceAll(removeTagTweetId, '')
    }

    static String removeTagUrl(String fileContent){
        return fileContent.replaceAll(removeTagUrl, '')
    }

    static String removeTagHashtag(String fileContent){
        return fileContent.replaceAll(removeTagHashtag, '')
    }

    static String removeTagDate(String fileContent){
        return fileContent.replaceAll(removeTagDate, '')
    }

    static String removeTagLocation(String fileContent){
        return fileContent.replaceAll(removeTagLocation, '')
    }

    static String removeInconsistence(String fileContent){
        return fileContent.replaceAll('@.*? ', '').replaceAll('https://.*?([;| ])', '$1')
    }

    static String formateColumns(String fileContent){
        fileContent = fileContent.replaceAll('(","location":.*?),("text")', '$1\n$2')
        return fileContent.replaceAll(';', '').replaceAll('","location":', ';')
    }

}
