package ir.cena.searchimage.constant

import ir.cena.searchimage.BuildConfig

class Constants {
    companion object {
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
        const val BASE_URL = "https://api.unsplash.com/"
        const val UNSPLASH_STARTING_PAGE_INDEX = 1
    }
}