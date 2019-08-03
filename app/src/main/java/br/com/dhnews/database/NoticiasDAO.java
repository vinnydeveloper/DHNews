package br.com.dhnews.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.dhnews.model.noticias.Article;
import io.reactivex.Flowable;

@Dao
public interface NoticiasDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("Delete from article")
    void deleteAll();

    @Query("Select * from article limit 30")
    Flowable<List<Article>> getAll(); // Aqui retornamos um Flowable que é o observavel para o ROOM DATABASE
}