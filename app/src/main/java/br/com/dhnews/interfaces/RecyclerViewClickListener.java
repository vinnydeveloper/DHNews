package br.com.dhnews.interfaces;

import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Usuario;

public interface RecyclerViewClickListener {

    void onClick(Noticias noticias);

    void onClick(Usuario usuario);

    void onClick();

}