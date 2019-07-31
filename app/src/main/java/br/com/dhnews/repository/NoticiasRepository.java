package br.com.dhnews.repository;

import br.com.dhnews.model.NoticiasResponse;
import br.com.dhnews.model.noticias.Noticias;
import br.com.dhnews.network.API;
import br.com.dhnews.network.ApiService;
import io.reactivex.Observable;
import io.reactivex.Single;

import static br.com.dhnews.network.ApiService.PUBLIC_KEY;


public class NoticiasRepository {

    public Single<Noticias> getNoticias() {
        return ApiService.getApiService().getNoticias("BR", PUBLIC_KEY);

    }

    public Observable<Noticias> searchItems(String item, int limite) {
        return ApiService.getApiService().buscaNoticia("q", 10, PUBLIC_KEY);
    }
}
