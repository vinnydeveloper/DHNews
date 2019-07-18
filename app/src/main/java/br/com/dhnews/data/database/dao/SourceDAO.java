package br.com.dhnews.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Source;
import io.reactivex.Flowable;

@Dao
public interface SourceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Source noticias);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Source> noticias);

    @Update
    void update(Source source);

    @Delete
    void delete(Source noticias);

    @Query("SELECT * FROM noticias")
    List<Source> getAll();

    @Query("SELECT * FROM noticias")
    Flowable<List<Source>> getAllRxJava();

    @Query("SELECT * FROM noticias WHERE id = :id")
    Source getById(long id);

    @Query("SELECT * FROM noticias WHERE name = name")
    Source getByName(String name);
}
