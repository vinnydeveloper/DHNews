package br.com.dhnews.repository;

import android.content.Context;

import java.util.List;

import br.com.dhnews.data.database.DatabaseRoom;
import br.com.dhnews.data.database.dao.NoticiasDAO;
import br.com.dhnews.data.network.ApiClient;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Source;
import io.reactivex.Flowable;

public class DhNewsRepository {

    // Pega os dados da base de dados
    public Flowable<List<Source>> getLocalResults(Context context) {
        DatabaseRoom room = DatabaseRoom.getDatabase(context);
        NoticiasDAO resultsDao = room.noticiasDAO();
        return resultsDao.getAllRxJava();
    }

    // Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Noticias> items) {
        DatabaseRoom room = DatabaseRoom.getDatabase(context);
        NoticiasDAO resultsDao = room.noticiasDAO();
        resultsDao.insert(items);
    }

    // Pega os items que virão da api do mercado livre
    public ApiClient searchItems(String country , String apikey) {
        return ApiClient.getApiService();
    }
}
