package examen2.examen2.examen2

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import examen2.examen2.examen2.MVVM.MoviesVideosViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieVideo : AppCompatActivity() {
    var id : String? = null
    var web : WebView? = null
    lateinit var moviesvideosviewModel: MoviesVideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videos_fragment)

        web = findViewById(R.id.web)

        web!!.settings.javaScriptEnabled = true


        id = intent.extras!!.getString("id")

        moviesvideosviewModel = ViewModelProvider(this).get(MoviesVideosViewModel::class.java)

        moviesvideosviewModel.MoviesVideos(id!!)

        observeViewModelVideos()

    }

    private fun observeViewModelVideos() {
        moviesvideosviewModel?.moviesvideos!!.observe(this) { movies ->
            val movieskey = movies!!.results!!.get(0)!!.key
            web!!.loadUrl("https://www.youtube.com/embed/"+movieskey+"?autoplay=1&vq=small")
        }
    }
}