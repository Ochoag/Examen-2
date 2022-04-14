package examen2.examen2.examen2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesService {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}