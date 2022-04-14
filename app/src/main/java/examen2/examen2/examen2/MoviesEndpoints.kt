package examen2.examen2.examen2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesEndpoints {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") api_key: String): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMoviesDetails(@Path("id") id : String, @Query("api_key") api_key: String): Call<MovDet>

    @GET("movie/{id}/images")
    fun getMoviesImages(@Path("id") id : String, @Query("api_key") api_key: String): Call<ImageBackdrops>

    @GET("movie/{id}/videos")
    fun getMoviesVideos(@Path("id") id : String, @Query("api_key") api_key: String): Call<MoviesVideosResponse>
}