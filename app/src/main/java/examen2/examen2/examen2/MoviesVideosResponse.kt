package examen2.examen2.examen2

import com.google.gson.annotations.SerializedName


data class MoviesVideosResponse(@SerializedName("results") var results: List<MovieKey?>? = null)
