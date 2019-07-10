package br.com.dhnews.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.dhnews.model.Usuario;
import io.reactivex.Flowable;

@Dao
public interface UsuariosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Usuario> usuarios);

    @Update
    void update(Usuario usuarios);

    @Delete
    void delete(Usuario usuarios);

    @Query("SELECT id, emailUsuario,senhaUsuario FROM usuarios")
    List<Usuario> getAll();

    @Query("SELECT id, emailUsuario,senhaUsuario FROM usuarios")
    Flowable<List<Usuario>> getAllRxJava();

    @Query("SELECT id, emailUsuario,senhaUsuario FROM usuarios WHERE id = :id")
    Usuario getById(long id);

    @Query("SELECT id, emailUsuario,senhaUsuario FROM usuarios WHERE emailUsuario = :emailUsuario")
    Usuario getByEmail(String emailUsuario);
}
