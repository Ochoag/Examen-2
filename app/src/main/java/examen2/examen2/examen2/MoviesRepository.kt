package examen2.examen2.examen2

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import examen2.examen2.examen2.db.dao.OfflineDataDAO
import examen2.examen2.examen2.db.database.AppDb
import examen2.examen2.examen2.db.entity.OfflineData

class MoviesRepository(application: Application) {
    private val offlineDataDao: OfflineDataDAO? = AppDb.getAppDb(application)!!.offlineDataDAO()

    fun insert(offlineData: OfflineData) {
        if (offlineDataDao != null) InsertAsyncTask(offlineDataDao).execute(offlineData)
    }

    fun getData(): LiveData<List<OfflineData>> {
        return offlineDataDao?.findAllOfflineData() ?: MutableLiveData()
    }

    private class InsertAsyncTask(private val offlineDataDAO: OfflineDataDAO) :
        AsyncTask<OfflineData, Void, Void>() {
        override fun doInBackground(vararg offlineData: OfflineData?): Void? {
            for (data in offlineData) {
                if (data != null) offlineDataDAO.insertOfflineData(data)
            }
            return null
        }
    }
}