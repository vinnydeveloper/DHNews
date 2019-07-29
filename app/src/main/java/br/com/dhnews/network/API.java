package br.com.dhnews.network;

import br.com.dhnews.model.NoticiasResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("top-headlines?")
    Single<NoticiasResponse> getNoticias(@Query("country") String country,
                                         @Query("apikey") String apikey
                                      //   @Query("title") String title
    );


}
