package br.com.dhnews.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.R;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Usuario;


public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private List<Noticias> listaNoticias;
    private RecyclerViewClickListener listener;
    private Usuario usuario;


    public NoticiasAdapter(List<Noticias> listaNoticias, RecyclerViewClickListener listener) {
        this.listaNoticias = listaNoticias;
        this.listener = listener;
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

        final Noticias noticias = listaNoticias.get(position);
        viewHolder.setaNoticiasNaTela(noticias);

        //Click na imagem da noticia para chamar o detalhe da noticia
        viewHolder.imagemNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(noticias);
            }
        });

        //Click no titulo da noticia para chamar o detalhe da noticia
        viewHolder.tituloNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(noticias);
            }
        });

        //Click na descrição da noticia para chamar o detalhe da noticia
        viewHolder.descricaoNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(noticias);
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


        //Atribui o as views os valores da variável contato
        public void setaNoticiasNaTela(Noticias noticias) {

            tituloNoticia.setText(noticias.getTituloNoticia());
            descricaoNoticia.setText(noticias.getDescricaoNoticia());
            horaNoticia.setText(noticias.getHoraNoticia());
            assuntoNoticia.setText(noticias.getAssuntoNoticia());
            imagemNoticias.setImageDrawable(ContextCompat.getDrawable(
                    imagemNoticias.getContext(), noticias.getImagemNoticias()));
        }
    }
}