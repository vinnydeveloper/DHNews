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

import br.com.dhnews.R;
import br.com.dhnews.RecyclerViewClickListener;
import br.com.dhnews.home.HomeActivity;
import br.com.dhnews.model.Noticias;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private List<Noticias> listaNoticias;
    private RecyclerViewClickListener listener; //ksc

    public NoticiasAdapter(List<Noticias> listaNoticias, RecyclerViewClickListener listener) {//ksc
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

    /*
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Noticias novaNoticia = listaNoticias.get(position);

        viewHolder.bind(novaNoticia);
    }
    */

    //ksc
    @Override
    public void onBindViewHolder(@NonNull final NoticiasAdapter.ViewHolder viewHolder, int position) {

        final Noticias noticias = listaNoticias.get(position);
        viewHolder.setaNoticiasNaTela(noticias);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onClick(noticias);


            }
        });
        //click na descrição da noticia como onclick
        viewHolder.descricaoNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tituloNoticia;
        TextView descricaoNoticia;
        TextView horaAssuntoNoticia;
        ImageView imagemNoticias;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloNoticia = itemView.findViewById(R.id.txtTitulo);
            descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
            horaAssuntoNoticia = itemView.findViewById(R.id.txtHora);
            imagemNoticias = itemView.findViewById(R.id.iconeNoticia);
        }

        /* ksc
        public void bind(Noticias noticia) {
            tituloNoticia.setText(noticia.getTituloNoticia());
            descricaoNoticia.setText(noticia.getDescricaoNoticia());
        }
        */

        //Atribui o as views os valores da variável contato
        public void setaNoticiasNaTela(Noticias noticias){
            tituloNoticia.setText(noticias.getTituloNoticia());
            descricaoNoticia.setText(noticias.getDescricaoNoticia());
            horaAssuntoNoticia.setText(noticias.getHoraAssuntoNoticia());
            imagemNoticias.setImageDrawable(ContextCompat.getDrawable(
                    imagemNoticias.getContext(),noticias.getImagemNoticias()));
        }
    }
}