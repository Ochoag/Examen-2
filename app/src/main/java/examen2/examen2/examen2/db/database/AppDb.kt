package examen2.examen2.examen2.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import examen2.examen2.examen2.constants.Constants
import examen2.examen2.examen2.db.dao.OfflineDataDAO
import examen2.examen2.examen2.db.entity.OfflineData

@Database(entities = [OfflineData::class], version = 2)
abstract class AppDb : RoomDatabase() {
    abstract fun offlineDataDAO(): OfflineDataDAO?

    companion object {
        private var INSTANCE: AppDb? = null
        fun getAppDb(context: Context): AppDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java, Constants.DATABASE_NAME
                )
                    .build()
            }
            return INSTANCE
        }
    }
}
