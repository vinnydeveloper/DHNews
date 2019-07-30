package br.com.dhnews.repository;

import br.com.dhnews.model.noticias.Noticias;
import br.com.dhnews.network.ApiService;
import io.reactivex.Single;

import static br.com.dhnews.network.ApiService.PUBLIC_KEY;

public class NoticiasRepository {

    public Single<Noticias> getNoticias() {
        return ApiService.getApiService().getNoticias("BR", PUBLIC_KEY);

    }
}
