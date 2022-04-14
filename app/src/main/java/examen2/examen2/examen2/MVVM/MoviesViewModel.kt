package examen2.examen2.examen2.MVVM

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import examen2.examen2.examen2.MovDet
import examen2.examen2.examen2.MoviesEndpoints
import examen2.examen2.examen2.MoviesResponse
import examen2.examen2.examen2.MoviesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var api_key : String = "cb2d74feb377bf9c0223502a42b73fbf"
    private var service: MoviesService
    val moviesdetails = MutableLiveData<MovDet>()


    fun MoviesDetails(idu : String) {
        val connectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                val moviesService = MoviesService()

                CoroutineScope(Dispatchers.IO).launch {
                    val response = moviesService.getRetrofit().create(MoviesEndpoints::class.java).getMoviesDetails(idu,api_key).execute()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            moviesdetails.postValue(response.body())
                        }
                    }
                }
            }
        }
    }


    init {
        service = MoviesService()
        MoviesDetails(idu = String())
    }
}
