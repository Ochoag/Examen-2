package examen2.examen2.examen2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import examen2.examen2.examen2.db.entity.OfflineData

@Dao
interface OfflineDataDAO {
    @Insert
    fun insertOfflineData(offlineData: OfflineData?)

    @Query("SELECT * FROM offline_data ORDER BY id DESC LIMIT 5")
    fun findAllOfflineData(): LiveData<List<OfflineData>>
}
