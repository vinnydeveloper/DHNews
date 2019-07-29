package br.com.dhnews.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.dhnews.model.Article;
import br.com.dhnews.model.NoticiasResponse;
import br.com.dhnews.repository.NoticiasRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoticiasViewModel extends AndroidViewModel {

    private MutableLiveData<List<Article>> resultLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private NoticiasRepository repository = new NoticiasRepository();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();



    public NoticiasViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Throwable> getErrorLiveData() {

        return errorLiveData;

    }

    public LiveData<List<Article>> getResults() {
        return resultLiveData;
    }

    public LiveData<Boolean> Loading(){
        return loadingLiveData;
    }

    public void getNoticias() {


        Single<NoticiasResponse> teste = repository.getNoticias();
        System.out.println(teste);
        disposable.add(repository.getNoticias()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((Disposable disposable) ->
                    loadingLiveData.setValue(true))
                .doAfterTerminate(() -> loadingLiveData.setValue(false))
                .subscribe(noticiasResponse -> resultLiveData.setValue(noticiasResponse.getArticles())
                , throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                        }));
                }

}
