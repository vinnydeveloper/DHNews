package br.com.dhnews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.interfaces.FavoriteItemClick;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.noticias.Source;

import static br.com.dhnews.util.AppUtil.formatarData;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.ViewHolder> {

    private List<Article> results;
    private FavoriteItemClick favoriteItemClick;

    public FavoritosAdapter(List<Article> results, FavoriteItemClick favoriteItemClick) {
        this.results = results;
        this.favoriteItemClick = favoriteItemClick;
    }

    @NonNull
    @Override
    public FavoritosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.item_favoritos, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Article result = results.get(position);
        viewHolder.bind(result);

        viewHolder.bookmarkFavorito.setOnClickListener(v -> favoriteItemClick.removeFavoriteClickListener(result));
    }

    public void update(List<Article> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public void remoteItem(Article result) {
        results.remove(result);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagemNoticia;
        private ImageView bookmarkFavorito;
        private TextView tituloNoticia;
        private TextView descricaoNoticia;
        private TextView horarioNoticia;
        private TextView fonteNoticia;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemNoticia = itemView.findViewById(R.id.imageViewNoticia);
            bookmarkFavorito = itemView.findViewById(R.id.bookmarkFavorito);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            descricaoNoticia = itemView.findViewById(R.id.conteudoNoticia);
            horarioNoticia = itemView.findViewById(R.id.horarioNoticia);
            fonteNoticia = itemView.findViewById(R.id.fonteNoticia);
        }

        public void bind(Article result) {
            Source autor = result.getSource();
            fonteNoticia.setText(autor.getName());
            tituloNoticia.setText(result.getTitle());
            horarioNoticia.setText(formatarData(result.getPublishedAt()));
            descricaoNoticia.setText(result.getDescription());

            if (result.getUrlToImage() != null) {

                Picasso.get().setIndicatorsEnabled(true);
                Picasso.get()
                        .load(result.getUrlToImage())
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imagemNoticia);
            }
        }
    }
}
