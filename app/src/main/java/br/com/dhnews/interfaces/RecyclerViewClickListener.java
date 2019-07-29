package br.com.dhnews.interfaces;

import br.com.dhnews.models.Noticias;
import br.com.dhnews.models.Usuario;

public interface RecyclerViewClickListener {

    void onClick(Noticias noticias);

    void onClick(Usuario usuario);
}