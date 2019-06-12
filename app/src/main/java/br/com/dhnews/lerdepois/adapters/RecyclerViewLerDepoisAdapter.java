package br.com.dhnews.lerdepois.adapters;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dhnews.MainActivy;
import br.com.dhnews.R;
import br.com.dhnews.lerdepois.interfaces.RecyclerViewClickListener;
import br.com.dhnews.lerdepois.model.Noticia;
import br.com.dhnews.lerdepois.views.LerDepoisActivity;
import br.com.dhnews.lerdepois.views.LerDepoisFragment;
import br.com.dhnews.usuario.view.UsuarioActivity;

public class RecyclerViewLerDepoisAdapter extends RecyclerView.Adapter<RecyclerViewLerDepoisAdapter.ViewHolder> {
    private List<Noticia> noticias;
    private RecyclerViewClickListener listener;


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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final Noticia noticia = noticias.get(i);
        viewHolder.setConteudoNaTela(noticia);

        viewHolder.imageViewNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(noticia);
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
        public ImageButton btnMarkButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnMarkButton = itemView.findViewById(R.id.btnBookMarkLerDepois);
            imageViewNoticia = itemView.findViewById(R.id.circleImageViewNoticia);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            conteudoNoticia = itemView.findViewById(R.id.conteudoNoticia);
            btnMarkButton = itemView.findViewById(R.id.btnBookMarkLerDepois);

        }

        public void setConteudoNaTela(Noticia noticia) {
            imageViewNoticia.setImageDrawable(ContextCompat.getDrawable(imageViewNoticia.getContext(), noticia.getImagemNoticia()));
            tituloNoticia.setText(noticia.getTituloText());
            conteudoNoticia.setText(noticia.getConteudoText());
        }

        public void dialog(final int position) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(itemView.getContext());
            alertDialog.setTitle("Remover Bookmark?");
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
                    removeItem(position);
                }
            });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();


        }

    }
}