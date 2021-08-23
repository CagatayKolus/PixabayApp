package com.cagataykolus.pixabayapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.pixabayapp.data.local.dao.HitDao
import com.cagataykolus.pixabayapp.model.Hit

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * HitDatabase provides DAO [HitDao] by using method [getHitDao].
 */
@Database(
    entities = [Hit::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class HitDatabase : RoomDatabase() {

    abstract fun getHitDao(): HitDao

    companion object {
        private const val DB_NAME = "database_hit"

        @Volatile
        private var INSTANCE: HitDatabase? = null

        fun getInstance(context: Context): HitDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HitDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_HIT).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
