package co.app.movieshowcardapp.networkContracter

import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @GET("/")
    fun getMovies(
        @Query("s")s:String,
        @Query("apikey")apikey:String
    ): Call<JsonObject>
}
