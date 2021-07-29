package com.romainbechard.linxotest.data

import com.romainbechard.linxotest.data.model.Album
import com.romainbechard.linxotest.data.model.AlbumDetail
import com.romainbechard.linxotest.data.model.Photo
import com.romainbechard.linxotest.data.model.User
import kotlinx.coroutines.CoroutineDispatcher
import com.romainbechard.linxotest.data.tools.Result
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MyRepository(
    private val api: AlbumsApi,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllAlbums(): Result<List<AlbumDetail>> = withContext(dispatcher) {
        return@withContext try {
            val users = api.getAllUsers()
            val userList = mutableListOf<Pair<Int, String>>()
            users.forEach { userList.add(it.userId to it.name) }

            val result = api.getAllAlbums()
            val list = mutableListOf<AlbumDetail>()
            result.forEach {
                list.add(
                    AlbumDetail(
                        title = it.title,
                        author = associateIdToName(it.userId, userList),
                        albumId = it.id,
                        userId = it.userId
                    )
                )
            }
            Result.Success(list)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    suspend fun getAllUsers(): Result<List<User>> = withContext(dispatcher) {
        return@withContext try {
            val result = api.getAllUsers()
            Result.Success(result)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    suspend fun getAlbumPhotos(albumId: Int): Result<List<Photo>> = withContext(dispatcher) {
        return@withContext try {
            val result = api.getAlbumPhotos(albumId)
            Result.Success(result)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    private fun associateIdToName(id: Int, list: List<Pair<Int, String>>): String {
        var result = ""
        for (item in list)
            if (id == item.first)
                result = item.second
        return result
    }

}