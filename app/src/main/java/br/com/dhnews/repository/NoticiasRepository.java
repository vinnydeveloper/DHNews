package br.com.dhnews.repository;

import br.com.dhnews.model.NoticiasResponse;
import br.com.dhnews.network.ApiService;
import io.reactivex.Single;

import static br.com.dhnews.network.ApiService.API_KEY;


public class NoticiasRepository {

    public Single<NoticiasResponse> getNoticias() {
        return ApiService.getApiService().getNoticias("br", API_KEY);

    }
}
