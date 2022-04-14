package examen2.examen2.examen2

import com.google.gson.annotations.SerializedName

data class Movie (@SerializedName("id") var id: String, @SerializedName("original_title") var original_title: String, @SerializedName("overview") var overview : String, @SerializedName("release_date") var release_date : String, @SerializedName("vote_average") var vote_average : String)