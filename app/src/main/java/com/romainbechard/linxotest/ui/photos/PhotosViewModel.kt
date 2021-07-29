package com.romainbechard.linxotest.ui.photos

import androidx.lifecycle.ViewModel
import com.romainbechard.linxotest.data.MyRepository

class PhotosViewModel : ViewModel() {

    private lateinit var repository: MyRepository


    fun configure(repository: MyRepository) {
        this.repository = repository
    }
}