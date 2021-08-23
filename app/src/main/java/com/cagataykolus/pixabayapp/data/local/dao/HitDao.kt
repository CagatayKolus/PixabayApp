package com.cagataykolus.pixabayapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.pixabayapp.model.Hit
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Data Access Object (DAO) for [com.cagataykolus.pixabayapp.data.local.HitDatabase]
 */
@Dao
interface HitDao {
    /**
     * Inserts [hits] into the [Hit.TABLE_HIT] table.
     * Duplicate values are replaced in the table.
     * @param hits
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHits(hits: List<Hit>)

    /**
     * Fetches all the data from the [Hit.TABLE_HIT] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Hit.TABLE_HIT}")
    fun getAllHits(): Flow<List<Hit>>

    /**
     * Deletes all the data from the [Hit.TABLE_HIT] table.
     */
    @Query("DELETE FROM ${Hit.TABLE_HIT}")
    suspend fun deleteAllHits()
}