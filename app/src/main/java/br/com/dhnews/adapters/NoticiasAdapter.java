package br.com.dhnews.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.R;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Usuario;


public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private List<Article> listaNoticias;
    private Noticias article;
    private RecyclerViewClickListener listener;
    private Usuario usuario;


    public NoticiasAdapter(List<Article> listaNoticias, Noticias article, RecyclerViewClickListener listener, Usuario usuario) {
        this.listaNoticias = listaNoticias;
        this.article = article;
        this.listener = listener;
        this.usuario = usuario;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.layout_lista_item_noticias, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoticiasAdapter.ViewHolder viewHolder, final int position) {

        final Article noticias = listaNoticias.get(position);
        viewHolder.setaNoticiasNaTela(noticias,article);

        //Click na imagem da noticia para chamar o detalhe da noticia
        viewHolder.imagemNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(article);
            }
        });

        //Click no titulo da noticia para chamar o detalhe da noticia
        viewHolder.tituloNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(article);
            }
        });

        //Click na descrição da noticia para chamar o detalhe da noticia
        viewHolder.descricaoNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(article);
            }
        });

        viewHolder.imagemBookMarkListaNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuarioLocal = new Usuario();

                if (usuario == null) {
                    usuarioLocal.setEmailUsuario("teste");
                }

                listener.onClick(usuarioLocal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tituloNoticia;
        TextView descricaoNoticia;
        TextView horaNoticia;
        TextView assuntoNoticia;
        ImageView imagemNoticias;
        ImageView imagemBookMarkListaNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloNoticia = itemView.findViewById(R.id.txtTitulo);
            descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
            horaNoticia = itemView.findViewById(R.id.txtHora);
            assuntoNoticia = itemView.findViewById(R.id.txtAssunto);
            imagemNoticias = itemView.findViewById(R.id.iconeNoticia);
            imagemBookMarkListaNoticia = itemView.findViewById(R.id.imagemBookMarkListaNoticia);
        }
        public void getImage(Article result){
            if (result.getUrlToImage() != null){
                Picasso.get().setIndicatorsEnabled(true);
                Picasso.get()
                        .load(result.getUrlToImage())
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imagemNoticias);
            }
        }

        //Atribui o as views os valores da variável contato
        public void setaNoticiasNaTela(Article noticias,Noticias not) {

            tituloNoticia.setText(noticias.getTitle());
            descricaoNoticia.setText(noticias.getDescription());
            horaNoticia.setText(noticias.getPublishedAt());
            assuntoNoticia.setText((CharSequence) not.getArticle().get(not.getArticle().indexOf(noticias)));
            //Imagem//
            getImage(noticias);
        }
    }
}