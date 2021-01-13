package ir.cena.searchimage.data

import android.util.Log
import androidx.paging.PagingSource
import ir.cena.searchimage.api.UnsplashApi
import ir.cena.searchimage.constant.Constants.Companion.UNSPLASH_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

/**
 * we don't use Dagger because query of sth we dont know compile time
 */
class UnsplashPagingFactory(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    /**
     * This method act as tiger for api request
     * and
     * turn data into pages
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        /**
         * for the first page params.key is null
         * because we haven't any page just for first time
         */

        /**
         * this position is for knowing where page
         */
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            Log.d("----", "$query $position ${params.loadSize}")
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }


    }


}