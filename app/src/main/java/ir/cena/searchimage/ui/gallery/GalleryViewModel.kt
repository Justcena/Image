package ir.cena.searchimage.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import ir.cena.searchimage.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
) :
    ViewModel() {

    companion object {
        private const val DEFAULT_VALUE = "new"
        private const val CURRENT_QUERY = "current_query"
    }

    /**
     * This is for saving state of searching
     * @Assisted is for it
     */
    private val _currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_VALUE)

    fun searchPhotos(query: String) {
        _currentQuery.value = query
    }

    /**
     * .cachetIn used for rotating device
     */
    val photos = _currentQuery.switchMap { queryString ->
        repository.getSearchResult(queryString).cachedIn(viewModelScope)
    }

}