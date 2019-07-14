package br.com.dhnews.api;

import br.com.dhnews.model.Noticias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Noticias> getNoticias(

            @Query("country") String country ,
            @Query("apiKey") String apiKey
    );
}
