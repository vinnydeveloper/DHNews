package br.com.dhnews.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.dhnews.model.noticias.Article;

@androidx.room.Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class) // Adicionamos os conversores
public abstract class Database extends RoomDatabase {

    // Criamos o DAO que será retornado
    public abstract NoticiasDAO resultsDAO();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "dhnews_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
