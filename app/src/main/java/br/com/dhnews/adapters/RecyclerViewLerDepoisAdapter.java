package br.com.dhnews.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;

public class RecyclerViewLerDepoisAdapter extends RecyclerView.Adapter<
        RecyclerViewLerDepoisAdapter.ViewHolder> {

    private List<Article> listaNoticias;
    private List<Noticias> listaArticle;
    private RecyclerViewClickListener listener;

    public ImageButton btnMarkButton;


    public RecyclerViewLerDepoisAdapter(List<Article> listaNoticias, List<Noticias> listaArticle, RecyclerViewClickListener listener) {
        this.listaNoticias = listaNoticias;
        this.listaArticle = listaArticle;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recyclerview_ler_depois_adapter, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLerDepoisAdapter.ViewHolder viewHolder, final int position) {

        final Article noticias = listaNoticias.get(position);
        final Noticias article = listaArticle.get(position);
        viewHolder.setConteudoNaTela(noticias,article);

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
        notifyItemChanged(position,listaNoticias.size());
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

            btnMarkButton = itemView.findViewById(R.id.btnBookMarkLerDepois);
            imagemNoticias = itemView.findViewById(R.id.circleImageViewNoticia);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            descricaoNoticia = itemView.findViewById(R.id.conteudoNoticia);
            assuntoNoticia = itemView.findViewById(R.id.assuntoNoticia);
            horaNoticia = itemView.findViewById(R.id.horarioNoticia);
            markRemove = itemView.findViewById(R.id.btnBookMarkLerDepois);

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

        public void setConteudoNaTela(Article noticias,Noticias not) {

            getImage(noticias);
            tituloNoticia.setText(noticias.getTitle());
            descricaoNoticia.setText(noticias.getDescription());
            assuntoNoticia.setText((CharSequence) not.getArticle().get(not.getArticle().indexOf(noticias)));
            horaNoticia.setText(noticias.getPublishedAt());
        }
    }
}