package examen2.examen2.examen2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import examen2.examen2.examen2.MVVM.MainViewModel
import examen2.examen2.examen2.MVVM.MoviesImagesViewModel
import examen2.examen2.examen2.MVVM.MoviesViewModel
import examen2.examen2.examen2.db.entity.OfflineData

class MoviesFragment : Fragment() {
    var id : String? = null
    var original_title : String? = null
    var overview : String? = null
    var release_date : String? = null
    var vote_average : String? = null
    var overviewtxt : TextView? = null
    var releasedatetxt : TextView? = null
    var voteaveragetxt : TextView? = null
    var img : ImageView? = null
    var vidbtn : Button? = null
    lateinit var viewModel: MainViewModel
    lateinit var moviesviewModel: MoviesViewModel
    lateinit var moviesimagesviewModel: MoviesImagesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        overviewtxt = view.findViewById(R.id.overview)
        releasedatetxt = view.findViewById(R.id.releasedate)
        voteaveragetxt = view.findViewById(R.id.voteaverage)
        img = view.findViewById(R.id.img)
        vidbtn = view.findViewById(R.id.seevideo)

        id = arguments?.getString("id")

        moviesviewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesviewModel.MoviesDetails(id!!)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observeViewModel()

        moviesimagesviewModel = ViewModelProvider(this).get(MoviesImagesViewModel::class.java)

        moviesimagesviewModel.MoviesImages(id!!)

        observeViewModelImages()

        vidbtn!!.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", id)
            val i1 = Intent(view.context, MovieVideo::class.java)
            i1.putExtra("id", id)
            startActivity(i1)
        }

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities == null) {
                addObserverDetails()
            }
        }
    }

    private fun observeViewModel() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
        moviesviewModel?.moviesdetails!!.observe(requireActivity()) { movies ->

                    val overview = movies!!.overview
                    overviewtxt!!.text = "Overview: " + overview
                    val release_date = movies!!.release_date
                    releasedatetxt!!.text = "Release date: " + release_date
                    val vote_average = movies!!.vote_average
                    voteaveragetxt!!.text = "Vote average: " + vote_average

                    id = arguments?.getString("id")
                    original_title = arguments?.getString("original_title")

                    viewModel.saveMovie(OfflineData(id, original_title, overview, release_date, vote_average))
                }
            }
        }
    }

    private fun observeViewModelImages() {
        moviesimagesviewModel?.moviesimages!!.observe(requireActivity()) { movies ->
            val img_path = movies!!.results!!.get(0)!!.file_path
            Glide.with(requireView().context).load("https://image.tmdb.org/t/p/original/"+img_path).into(img!!)
        }
    }

    private fun addObserverDetails() {
        vidbtn!!.visibility = View.GONE

        overview = arguments?.getString("overview")
        release_date = arguments?.getString("release_date")
        vote_average = arguments?.getString("vote_average")

        overviewtxt!!.text = "Overview: " + overview
        releasedatetxt!!.text = "Release date: " + release_date
        voteaveragetxt!!.text = "Vote average: " + vote_average
    }
}
