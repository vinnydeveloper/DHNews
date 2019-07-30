package br.com.dhnews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.repository.NoticiasRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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

        disposable.add(
                repository.getNoticias()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(noticias -> resultLiveData.setValue(noticias.getArticles())
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

}
