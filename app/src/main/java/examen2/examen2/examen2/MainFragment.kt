package examen2.examen2.examen2

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import examen2.examen2.examen2.MVVM.MainViewModel
import examen2.examen2.examen2.db.entity.OfflineData


class MainFragment : Fragment(), OnMVItemClickListner {

    var recy : RecyclerView? = null
    var reclm : RecyclerView.LayoutManager? = null
    var array : ArrayList<OfflineData> = ArrayList()
    lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recy = view.findViewById(R.id.movies)
        reclm = LinearLayoutManager(view.context)
        recy!!.layoutManager = reclm

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observeViewModel()

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities == null) {
                addObserver()
            }
        }

    }

    private fun observeViewModel() {
        viewModel?.moviesname!!.observe(requireActivity()) { movies ->
            array.clear()
            if (movies != null) {
                for (i in 0 until movies!!.results!!.size) {
                    array.add(
                       OfflineData(movies.results!!.get(i)!!.id, movies.results!!.get(i)!!.original_title, movies.results!!.get(i)!!.overview, movies.results!!.get(i)!!.release_date, movies.results!!.get(i)!!.vote_average)
                    )
                    recy!!.adapter = MoviesAdapter(array, this@MainFragment)
                }
            }
        }
    }

    private fun addObserver() {
        array.clear()
        val observer = Observer<List<OfflineData>> { movies ->
            if (movies != null) {
                for (movie in movies) {
                    array.add(
                        OfflineData(movie.idmovie!!, movie.original_title!!, movie.overview!!, movie.release_date!!, movie.vote_average!!)
                    )
                    recy!!.adapter = MoviesAdapter(array, this@MainFragment)
                }
            }
        }
        viewModel.data.observe(viewLifecycleOwner, observer)
    }

    override fun onItemClick(item: OfflineData, position: Int) {
        val bundle = Bundle()
        bundle.putString("id", item.idmovie)
        bundle.putString("original_title", item.original_title)
        bundle.putString("overview", item.overview)
        bundle.putString("release_date", item.release_date)
        bundle.putString("vote_average", item.vote_average)

        view?.let { Navigation.findNavController(it).navigate(R.id.action_gotodetails, bundle) }
    }
}