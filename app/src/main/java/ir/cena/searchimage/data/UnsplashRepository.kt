package ir.cena.searchimage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ir.cena.searchimage.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {

    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingFactory(unsplashApi, query) }
        ).liveData
}