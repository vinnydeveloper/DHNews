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
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.noticias.Source;
import br.com.dhnews.view.Noticias.DetalheNoticiaActivity;

import static br.com.dhnews.util.AppUtil.formatarData;


public class RecyclerViewNoticiasAdapter extends RecyclerView.Adapter<RecyclerViewNoticiasAdapter.ViewHolder> {

    private List<Article> listaNoticias;


    public RecyclerViewNoticiasAdapter(List<Article> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.layout_lista_item_noticias, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Article result = listaNoticias.get(position);
        viewHolder.bind(result);

        viewHolder.itemView.setOnClickListener(v -> {
        String transitionName = "image_" + position;
            Intent intent = new Intent(viewHolder.itemView.getContext(),
                    DetalheNoticiaActivity.class);
            intent.putExtra("NOTICIAS", result);
            intent.putExtra("transitionName", transitionName);

            viewHolder.imagemNoticias.setTransitionName(transitionName);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) viewHolder.itemView.getContext(),
                            viewHolder.imagemNoticias, transitionName);

            viewHolder.itemView.getContext().startActivity(intent, options.toBundle());
        });

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }
 {
    }


    class ViewHolder extends RecyclerView.ViewHolder {

       private TextView tituloNoticia;
       private TextView descricaoNoticia;
       private TextView horaNoticia;
       private TextView categoriaNoticia;
       private ImageView imagemNoticias;
       private ImageView imagemBookMarkListaNoticia;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tituloNoticia = itemView.findViewById(R.id.txtTitulo);
           descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
           horaNoticia = itemView.findViewById(R.id.txtHora);
           categoriaNoticia = itemView.findViewById(R.id.txtFonte);
           imagemNoticias = itemView.findViewById(R.id.iconeNoticia);
           imagemBookMarkListaNoticia = itemView.findViewById(R.id.imagemBookMarkListaNoticia);
       }

       public void bind(Article result) {


           Source autor = result.getSource();
           categoriaNoticia.setText(autor.getName());
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
        public void update(List<Article> resultList) {
            this.listaNoticias = resultList;
            notifyDataSetChanged();

        }

        public void clear(){
        this.listaNoticias.clear();
        notifyDataSetChanged();
        }
    }
