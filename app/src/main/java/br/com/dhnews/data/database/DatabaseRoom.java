package br.com.dhnews.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.dhnews.data.database.dao.NoticiasDAO;
import br.com.dhnews.data.database.dao.UsuariosDAO;
import br.com.dhnews.model.Source;
import br.com.dhnews.model.Usuario;

//import br.com.dhnews.data.database.dao.NoticiasDAO;

@android.arch.persistence.room.Database(entities = {Source.class,Usuario.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DatabaseRoom extends RoomDatabase {
    private static volatile DatabaseRoom INSTANCE;

    public abstract NoticiasDAO noticiasDAO();

    public abstract UsuariosDAO usuariosDAO();

    public static DatabaseRoom getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, DatabaseRoom.class, "dh_news")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}