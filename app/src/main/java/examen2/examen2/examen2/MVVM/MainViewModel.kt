package examen2.examen2.examen2.MVVM

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import examen2.examen2.examen2.*
import examen2.examen2.examen2.db.entity.OfflineData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var api_key : String = "cb2d74feb377bf9c0223502a42b73fbf"
    private var service: MoviesService
    val moviesname = MutableLiveData<MoviesResponse>()

    fun Movies() {
        val connectivityManager =
            context.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                val moviesService = MoviesService()

                CoroutineScope(Dispatchers.IO).launch {
                    val response = moviesService.getRetrofit().create(MoviesEndpoints::class.java).getMovies(api_key).execute()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            moviesname.postValue(response.body())
                        }
                    }
                }
            }
        }
    }

    private val repository = MoviesRepository(application)
    val data = repository.getData()

    fun saveMovie(data: OfflineData) {
        repository.insert(data)
    }

    init {
        service = MoviesService()
        Movies()
    }
}
