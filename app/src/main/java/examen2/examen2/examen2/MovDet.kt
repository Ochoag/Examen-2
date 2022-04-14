package examen2.examen2.examen2

import com.google.gson.annotations.SerializedName

data class MovDet (@SerializedName("overview") var overview : String, @SerializedName("release_date") var release_date: String, @SerializedName("vote_average") var vote_average: String)