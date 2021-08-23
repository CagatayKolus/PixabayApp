package com.cagataykolus.pixabayapp.data.repository

import com.cagataykolus.pixabayapp.model.Image
import com.cagataykolus.pixabayapp.data.local.dao.HitDao
import com.cagataykolus.pixabayapp.data.remote.api.PixabayService
import com.cagataykolus.pixabayapp.model.Hit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
interface HitRepository {
    fun getAllHits(
        query: String
    ): Flow<Resource<List<Hit>>>

    fun deleteAllHits(
    ): Flow<Resource<List<Hit>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultHitRepository @Inject constructor(
    private val dao: HitDao,
    private val service: PixabayService
) : HitRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllHits(
        query: String
    ): Flow<Resource<List<Hit>>> {
        return object : NetworkBoundRepository<List<Hit>, Image>() {

            override suspend fun saveRemoteData(response: Image) = dao.addHits(response.hits)

            override fun fetchFromLocal(): Flow<List<Hit>> = dao.getAllHits()

            override suspend fun fetchFromRemote(): Response<Image> =
                service.getImage(query = query)

        }.asFlow()
    }
    /**
     * Deletes all data.
     */
    override fun deleteAllHits(): Flow<Resource<List<Hit>>> {
        return object : NetworkBoundRepository<List<Hit>, Image>() {

            override suspend fun saveRemoteData(response: Image) = dao.deleteAllHits()

            override fun fetchFromLocal(): Flow<List<Hit>> {
                return dao.getAllHits()
            }

            override suspend fun fetchFromRemote(): Response<Image> {
                return service.getImage(query = "")
            }

        }.asFlow()
    }
}