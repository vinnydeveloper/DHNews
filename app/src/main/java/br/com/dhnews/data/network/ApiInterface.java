package br.com.dhnews.data.network;

import br.com.dhnews.model.Noticias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Noticias> searchItem(

            @Query("country") String country ,
            @Query("apiKey") String apiKey
    );

}
