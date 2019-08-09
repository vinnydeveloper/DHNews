package br.com.dhnews.repository;

import android.content.Context;

import java.util.List;

import br.com.dhnews.database.Database;
import br.com.dhnews.database.NoticiasDAO;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.noticias.Noticias;
import br.com.dhnews.network.ApiService;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static br.com.dhnews.network.ApiService.PUBLIC_KEY;


public class NoticiasRepository {

    // Pega os dados da base de dados
    public Flowable<List<Article>> getLocalResults(Context context) {
        Database room = Database.getDatabase(context);
        NoticiasDAO resultsDao = room.resultsDAO();
        return resultsDao.getAll();
    }

    // Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Article> items) {
        Database room = Database.getDatabase(context);
        NoticiasDAO resultsDao = room.resultsDAO();
        resultsDao.insert(items);
    }

    public Single<Noticias> getNoticias() {
        return ApiService.getApiService().getNoticias("BR", PUBLIC_KEY);

    }

    public Observable<Noticias> searchItems(String item, int limite) {
        return ApiService.getApiService().buscaNoticia(item, 10,"pt", PUBLIC_KEY);
    }
    public Observable<Noticias> getNoticiasFavoritas(String categoria, int limite) {
        return ApiService.getApiService().buscaNoticia(categoria, 2,"pt", PUBLIC_KEY);
    }
}
