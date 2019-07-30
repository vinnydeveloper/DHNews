package br.com.dhnews.network;

import br.com.dhnews.model.noticias.Noticias;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("top-headlines")
    Single<Noticias> getNoticias(@Query("country") String country,
                                 @Query("apikey") String apikey
                                 //   @Query("title") String title
    );


}
