package br.com.dhnews.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

//import br.com.dhnews.data.database.dao.NoticiasDAO;
import br.com.dhnews.data.database.dao.SourceDAO;
import br.com.dhnews.data.database.dao.UsuariosDAO;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Source;
import br.com.dhnews.model.Usuario;

@android.arch.persistence.room.Database(entities = {Source.class,Usuario.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class Database extends RoomDatabase {
    private static volatile Database INSTANCE;

    public abstract SourceDAO noticiasDAO();

    public abstract UsuariosDAO usuariosDAO();

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "dh_news")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}