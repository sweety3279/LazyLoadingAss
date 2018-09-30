package motobeans.architecture.constants

/**
 * Created by swati on 24/9/2018.
 */
class Constants {

    private fun Constants(): Unit {}


    object Injection {
        const val API_CURRENT_URL = "currentURL"
        const val API_DEVELOPMENT_URL = "developmentURL"
        const val API_TESTING_URL = "testingURL"
        const val API_LIVE_URL = "liveURL"
        const val API_PRODUCTION_URL = "productionURL"


        /**
         * Network Class v1 constants
         */
        const val RETROFIT_V1 = "RETROFIT_V1"
        const val GSON_V1 = "GSON_V1"
        const val ENDPOINT_V1 = "GSON_V1"
        const val OKHHTP_CACHE_V1 = "OKHTTP_CACHE_V1"
        const val OKHHTP_CLIENT_V1 = "OKHTTP_CLIENT_V1"
        const val INTERCEPTOR_HEADER_V1 = "INTERCEPTOR_HEADER_V1"
        const val INTERCEPTOR_LOGGING_V1 = "INTERCEPTOR_LOGGING_V1"
    }

    object API {

        object URL {

            const val URL_DEVELOPMENT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
            const val URL_TESTING = ""
            const val URL_LIVE = ""
            const val URL_PRODUCTION = ""
        }
    }

    object APP {
        const val TEMP = "temp"
    }
}