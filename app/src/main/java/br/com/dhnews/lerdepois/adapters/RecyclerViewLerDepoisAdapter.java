package br.com.dhnews.lerdepois.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.model.Noticias;

public class RecyclerViewLerDepoisAdapter extends RecyclerView.Adapter<
        RecyclerViewLerDepoisAdapter.ViewHolder> {

    private List<Noticias> listaNoticias;
    private RecyclerViewClickListener listener;

    public ImageButton btnMarkButton;

    public RecyclerViewLerDepoisAdapter(List<Noticias> listaNoticias,
                                        RecyclerViewClickListener listener) {
        this.listaNoticias = listaNoticias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_favoritos, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLerDepoisAdapter.ViewHolder viewHolder, final int position) {

        final Noticias noticias = listaNoticias.get(position);
        viewHolder.setConteudoNaTela(noticias);

        /*
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(noticias);

            }
        });
        */

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

        viewHolder.markRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //caixa de dialogo
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Remover Notícia?");
                alertDialog.setMessage("");
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("MANTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove de fato a noticia da pposiçao selecionada
                        removeItem(position);

                    }
                });

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public void removeItem(int position) {
        listaNoticias.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tituloNoticia;
        TextView descricaoNoticia;
        TextView horaNoticia;
        TextView assuntoNoticia;
        ImageView imagemNoticias;
        ImageButton markRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnMarkButton = itemView.findViewById(R.id.bookmarkFavorito);
            imagemNoticias = itemView.findViewById(R.id.imageViewNoticia);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            descricaoNoticia = itemView.findViewById(R.id.conteudoNoticia);
            assuntoNoticia = itemView.findViewById(R.id.fonteNoticia);
            horaNoticia = itemView.findViewById(R.id.horarioNoticia);
            markRemove = itemView.findViewById(R.id.bookmarkFavorito);

        }

        public void setConteudoNaTela(Noticias noticias) {

            imagemNoticias.setImageDrawable(ContextCompat.getDrawable(
                    imagemNoticias.getContext(), noticias.getImagemNoticias()));
            tituloNoticia.setText(noticias.getTituloNoticia());
            descricaoNoticia.setText(noticias.getDescricaoNoticia());
            assuntoNoticia.setText(noticias.getAssuntoNoticia());
            horaNoticia.setText(noticias.getHoraNoticia());
        }
    }
}