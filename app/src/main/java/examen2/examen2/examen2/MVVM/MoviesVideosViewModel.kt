package examen2.examen2.examen2.MVVM

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import examen2.examen2.examen2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesVideosViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var api_key : String = "cb2d74feb377bf9c0223502a42b73fbf"
    private var service: MoviesService
    val moviesvideos = MutableLiveData<MoviesVideosResponse>()


    fun MoviesVideos(idu : String) {
        val connectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                val moviesService = MoviesService()

                CoroutineScope(Dispatchers.IO).launch {
                    val response = moviesService.getRetrofit().create(MoviesEndpoints::class.java).getMoviesVideos(idu,api_key).execute()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            moviesvideos.postValue(response.body())
                        }
                    }
                }
            }
        }
    }


    init {
        service = MoviesService()
        MoviesVideos(idu = String())
    }
}
