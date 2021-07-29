package com.romainbechard.linxotest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romainbechard.linxotest.data.MyRepository
import com.romainbechard.linxotest.data.model.Album
import com.romainbechard.linxotest.data.model.AlbumDetail
import com.romainbechard.linxotest.data.tools.Result
import com.romainbechard.linxotest.tools.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private lateinit var repository: MyRepository

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _albumList: MutableLiveData<List<AlbumDetail>> = MutableLiveData(listOf())
    val albumList = _albumList

    private val _goToPhotosEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    val goToPhotosEvent = _goToPhotosEvent

    private suspend fun fetchAllAlbums(): List<AlbumDetail> {
        val list:MutableList<AlbumDetail> = mutableListOf()
            when (val result = repository.getAllAlbums()) {
                is Result.Success -> {
                    Timber.d("Success while fetching data")
                    _loading.value = false
                    result.data.forEach { list.add(it) }
                }
                is Result.Error -> _error.value = true
            }
        Timber.d("List = $list")
        return list
    }

    fun onAlbumClicked(album: AlbumDetail) {
        _goToPhotosEvent.value = Event(album.albumId)
    }

    fun configure(repository: MyRepository) {
        this.repository = repository
        viewModelScope.launch {
            _albumList.value = fetchAllAlbums()
        }
    }

    init {
        _error.value = false
        _loading.value = true
    }
}