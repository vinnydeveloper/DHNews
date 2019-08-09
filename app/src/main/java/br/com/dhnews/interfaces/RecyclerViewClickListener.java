package br.com.dhnews.interfaces;

import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.Usuario;

public interface RecyclerViewClickListener {

    void onClick(Article article);

    void onClick(Usuario usuario);
}