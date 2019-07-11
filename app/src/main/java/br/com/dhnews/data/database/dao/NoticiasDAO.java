package br.com.dhnews.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.dhnews.model.Noticias;
import io.reactivex.Flowable;

@Dao
public interface NoticiasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Noticias noticias);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Noticias> noticias);

    @Update
    void update(Noticias noticias);

    @Delete
    void delete(Noticias noticias);

    @Query("SELECT * FROM noticias")
    List<Noticias> getAll();

    @Query("SELECT * FROM noticias")
    Flowable<List<Noticias>> getAllRxJava();

    @Query("SELECT * FROM noticias WHERE id = :id")
    Noticias getById(long id);

    @Query("SELECT * FROM noticias WHERE tituloNoticia = :tituloNoticia")
    Noticias getByTitle(String tituloNoticia);
}
