package examen2.examen2.examen2

import com.google.gson.annotations.SerializedName


data class ImageBackdrops(@SerializedName("backdrops") var results: List<FilePathImage?>? = null)
