package br.com.dhnews.lerdepois.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.lerdepois.interfaces.RecyclerViewClickListener;
import br.com.dhnews.lerdepois.model.Noticia;

public class RecyclerViewLerDepoisAdapter extends RecyclerView.Adapter<RecyclerViewLerDepoisAdapter.ViewHolder> {
    private List<Noticia> noticias;
    private RecyclerViewClickListener listener;
    public ImageButton btnMarkButton;

    public RecyclerViewLerDepoisAdapter(List<Noticia> noticias, RecyclerViewClickListener listener) {
        this.noticias = noticias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_ler_depois_adapter, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {


        final Noticia noticia = noticias.get(position);
        viewHolder.setConteudoNaTela(noticia);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(noticia);

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
        return noticias.size();
    }

    public void removeItem(int position) {
        noticias.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewNoticia;
        private TextView tituloNoticia;
        private TextView conteudoNoticia;
        private TextView assunto;
        private TextView horaNoticia;
        private ImageButton markRemove;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            btnMarkButton = itemView.findViewById(R.id.btnBookMarkLerDepois);
            imageViewNoticia = itemView.findViewById(R.id.circleImageViewNoticia);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            conteudoNoticia = itemView.findViewById(R.id.conteudoNoticia);
            assunto = itemView.findViewById(R.id.assuntoNoticia);
            horaNoticia = itemView.findViewById(R.id.horarioNoticia);
            markRemove = itemView.findViewById(R.id.btnBookMarkLerDepois);


        }

        public void setConteudoNaTela(Noticia noticia) {
            imageViewNoticia.setImageDrawable(ContextCompat.getDrawable(imageViewNoticia.getContext(), noticia.getImagemNoticia()));
            tituloNoticia.setText(noticia.getTituloText());
            conteudoNoticia.setText(noticia.getConteudoText());
            assunto.setText(noticia.getAssunto());
            horaNoticia.setText(noticia.getTempoNoticia());
        }
    }
}