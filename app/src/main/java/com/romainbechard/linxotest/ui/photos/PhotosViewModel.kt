package com.romainbechard.linxotest.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romainbechard.linxotest.data.MyRepository
import com.romainbechard.linxotest.data.model.Photo
import com.romainbechard.linxotest.data.tools.Result
import com.romainbechard.linxotest.tools.Event
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {

    private lateinit var repository: MyRepository
    private var albumId = -1

    var photoUrlList: MutableList<Photo> = mutableListOf()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _photoFetchedEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val photoFetchedEvent = _photoFetchedEvent

    fun configure(repository: MyRepository, idAlbum: Int) {
        albumId = idAlbum
        this.repository = repository
        viewModelScope.launch {
            photoUrlList = fetchPhotos().toMutableList()
            _photoFetchedEvent.value = Event(Unit)
            _loading.value = false
        }
    }

    private suspend fun fetchPhotos(): List<Photo>{
        return when(val result = repository.getAlbumPhotos(albumId)) {
            is Result.Success -> {
                result.data
            }
            else -> {
                emptyList()
            }
        }
    }

    init {
        _loading. value = true
    }
}