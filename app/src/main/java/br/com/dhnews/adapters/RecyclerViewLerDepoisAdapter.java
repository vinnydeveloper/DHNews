package br.com.dhnews.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.interfaces.FavoriteItemClick;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.noticias.Noticias;
import br.com.dhnews.model.noticias.Source;
import br.com.dhnews.view.lerdepois.DetalheLerDepoisActivity;
import br.com.dhnews.view.noticias.DetalheNoticiaActivity;

import static br.com.dhnews.util.AppUtil.formatarData;

public class RecyclerViewLerDepoisAdapter extends RecyclerView.Adapter<RecyclerViewLerDepoisAdapter.ViewHolder> {
    private List<Article> listaNoticias;
    private FavoriteItemClick favoriteItemClick;


    public RecyclerViewLerDepoisAdapter(List<Article> listaNoticias, FavoriteItemClick favoriteItemClick) {
        this.listaNoticias = listaNoticias;
        this.favoriteItemClick = favoriteItemClick;

    }

    @NonNull
    @Override
    public RecyclerViewLerDepoisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_ler_depois, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLerDepoisAdapter.ViewHolder viewHolder, int position) {
        Article result = listaNoticias.get(position);
        viewHolder.bind(result);

        viewHolder.itemView.setOnClickListener(v -> {
            String transitionName = "image_" + position;
            Intent intent = new Intent(viewHolder.itemView.getContext(),
                    DetalheLerDepoisActivity.class);
            intent.putExtra("NOTICIAS", result);
            intent.putExtra("transitionName", transitionName);

            viewHolder.imagemNoticias.setTransitionName(transitionName);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) viewHolder.itemView.getContext(),
                            viewHolder.imagemNoticias, transitionName);

            viewHolder.itemView.getContext().startActivity(intent, options.toBundle());
        });
    }

    public void update(List<Article> resultList) {
        this.listaNoticias = resultList;
        notifyDataSetChanged();

    }

    public void removeItem(Noticias result) {
        listaNoticias.remove(result);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloNoticia;
        private TextView descricaoNoticia;
        private TextView horaNoticia;
        private TextView fonteNoticia;
        private ImageView imagemNoticias;
        private ImageView imagemBookMarkListaNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloNoticia = itemView.findViewById(R.id.txtTitulo);
            descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
            horaNoticia = itemView.findViewById(R.id.txtHora);
            fonteNoticia = itemView.findViewById(R.id.txtFonte);
            imagemNoticias = itemView.findViewById(R.id.iconeNoticia);
            imagemBookMarkListaNoticia = itemView.findViewById(R.id.imageBookMarkFavorito);
        }

        public void bind(Article result) {


            //Source autor = result.getSource();
            //fonteNoticia.setText(autor.getName());
            tituloNoticia.setText(result.getTitle());
            horaNoticia.setText(formatarData(result.getPublishedAt()));
            descricaoNoticia.setText(result.getDescription());

            if (result.getUrlToImage() != null) {

                Picasso.get().setIndicatorsEnabled(true);
                Picasso.get()
                        .load(result.getUrlToImage())
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imagemNoticias);
            }
        }
    }


}

