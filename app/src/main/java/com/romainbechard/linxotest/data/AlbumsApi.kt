package com.romainbechard.linxotest.data

import com.romainbechard.linxotest.data.model.Album
import com.romainbechard.linxotest.data.model.Photo
import com.romainbechard.linxotest.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsApi {

    @GET("/albums")
    suspend fun getAllAlbums(): List<Album>

    @GET("/users")
    suspend fun getAllUsers(): List<User>

    @GET("/photos")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Int): List<Photo>

}