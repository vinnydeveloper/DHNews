package br.com.dhnews.network;

import br.com.dhnews.model.noticias.Noticias;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("top-headlines")
    Single<Noticias> getNoticias(@Query("country") String country,
                                 @Query("apikey") String apikey
                                 //   @Query("title") String title
    );

    @GET("everything")
    Observable<Noticias> buscaNoticia(@Query("q") String item,
                                      @Query("pageSize") int limite,
                                      @Query("language") String country,
                                      @Query("apikey") String apikey);
    @GET("top-headlines")
    Single<Noticias> getNoticias(@Query("category") String categoria,
                                 @Query("pageSize") int limite,
                                 @Query("language") String country,
                                 @Query("apikey") String apikey);


}
