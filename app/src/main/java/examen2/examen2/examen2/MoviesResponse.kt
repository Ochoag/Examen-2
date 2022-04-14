package examen2.examen2.examen2

import com.google.gson.annotations.SerializedName


data class MoviesResponse(@SerializedName("results") var results: List<Movie?>? = null)
