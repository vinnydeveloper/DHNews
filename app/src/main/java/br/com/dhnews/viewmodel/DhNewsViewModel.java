package br.com.dhnews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import br.com.dhnews.data.database.DatabaseRoom;
import br.com.dhnews.data.database.dao.NoticiasDAO;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.repository.DhNewsRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.dhnews.Util.AppUtil.isNetworkConnected;

public class DhNewsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Article>> resultLiveData = new MutableLiveData<List<Article>>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private DhNewsRepository repository = new DhNewsRepository();
    private Object List;
    private Object Article;
    private Object Noticias;

    public DhNewsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Article>> getResultLiveData() {
        return resultLiveData;
    }

    // Ao buscar o item verificamos se estamos conectados ou não
    public void searchItem(String country, String apikey) {
        if (isNetworkConnected(getApplication())) {
            getFromNetwork(country,apikey);
        } else {
            getFromLocal();
        }
    }

    private void getFromLocal() {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.getLocalResults(getApplication().getApplicationContext())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> {
                    // setar livedata para mostrar loading
                })
                .doAfterTerminate(() -> {
                    // setar livedata para esconmder loading
                })
                .subscribe(results -> {
                    resultLiveData.setValue(resultLiveData.getValue());
                }, throwable -> {
                    // setar livedata para mostrar error
                })
        );

    }

    private void getFromNetwork(String country, String apikey) {

        // Adicionamos a chamada a um disposible para podermos eliminar o disposable da destruição do viewmodel
        disposable.add(
                repository.searchItems(country,apikey)
                        .subscribeOn(Schedulers.newThread())
                        .map(this::saveItems)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscription -> {
                            // setar livedata para mostrar loading
                        })
                        .doAfterTerminate(() -> {
                            // setar livedata para esconmder loading
                        })
                        .subscribe(noticias -> {
                            resultLiveData.setValue(List<Article>);
                        }, throwable -> {
                            // setar livedata para mostrar error
                        })
        );
        //Salvar os items quando tivermos buscado

    }

    private Noticias saveItems(Noticias noticias) {
        NoticiasDAO dao = DatabaseRoom.getDatabase(getApplication().getApplicationContext())
                .noticiasDAO();

                dao.deleteAll();
                dao.insert((List<Noticias> noticias));

        return noticias;
    }

    // Limpa as chamadas que fizemos no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
