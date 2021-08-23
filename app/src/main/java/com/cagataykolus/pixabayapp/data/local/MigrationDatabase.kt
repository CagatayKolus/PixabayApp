package com.cagataykolus.pixabayapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.pixabayapp.model.Hit

/**
 * Created by Çağatay Kölüş on 21.08.2021.
 * cagataykolus@gmail.com
 */
/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_HIT: Array<Migration>
        get() = arrayOf(
            migrationHit()
        )

    /**
     *  Creates a new migration between version 1 and 2 for [Hit.TABLE_HIT] table.
     */
    private fun migrationHit(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Hit.TABLE_HIT} ADD COLUMN body TEXT")
        }
    }
}
