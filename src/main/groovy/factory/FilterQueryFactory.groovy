package factory

import twitter4j.FilterQuery

class FilterQueryFactory {

    static FilterQuery filterQuery = new FilterQuery()

    static FilterQuery getInstance() {
        final String[] keyword = ["bolsonaro2018", "lula2018"]
        final String language = "pt"

        filterQuery.language(language)
        filterQuery.track(keyword)

        printaLog(keyword, language)
        return filterQuery
    }

    static void printaLog(String[] filters, String language) {
        Calendar calendar = Calendar.getInstance()
        print("[${calendar.getTime()}]Query{ language: ${language} ")
        for(String filter: filters){
            print("filter: ${filter} ")
        }
        println("}")
    }
}