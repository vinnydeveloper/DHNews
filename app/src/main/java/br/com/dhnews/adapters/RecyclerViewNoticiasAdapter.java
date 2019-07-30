package br.com.dhnews.adapters;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.common.android.FragmentCompat;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.R;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Usuario;
import br.com.dhnews.view.DetalheNoticiaActivity;


public class RecyclerViewNoticiasAdapter extends RecyclerView.Adapter<RecyclerViewNoticiasAdapter.ViewHolder> {

    private List<Article> listaNoticias;


    public RecyclerViewNoticiasAdapter(List<Article> newsList) {
        this.listaNoticias = newsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_item_noticias, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article result = this.listaNoticias.get(position);
        holder.bind(result);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            String transitionName = "image_" + position;
//                Intent intent = new Intent(holder.itemView.getContext(),
//                        DetalheNoticiaActivity.class);
//                intent.putExtra("noticia", result);
//                intent.putExtra("transitionName", transitionName);
//
//                holder.imagemNoticias.setTransitionName(transitionName);
//
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
//                                holder.imagemNoticias, transitionName);
//
//                holder.itemView.getContext().startActivity(intent, options.toBundle());
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }


   class ViewHolder extends RecyclerView.ViewHolder {

       private TextView tituloNoticia;
       private TextView descricaoNoticia;
       private TextView horaNoticia;
       private TextView categoriaNoticia;
       private ImageView imagemNoticias;
       private ImageView imagemBookMarkListaNoticia;

       ViewHolder(View itemView) {
           super(itemView);
           tituloNoticia = itemView.findViewById(R.id.txtTitulo);
           descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
           horaNoticia = itemView.findViewById(R.id.txtHora);
           categoriaNoticia = itemView.findViewById(R.id.txtAssunto);
           imagemNoticias = itemView.findViewById(R.id.iconeNoticia);
           imagemBookMarkListaNoticia = itemView.findViewById(R.id.imagemBookMarkListaNoticia);
       }

       public void bind(Article result) {
           tituloNoticia.setText(result.getTitle());
           horaNoticia.setText(result.getPublishedAt());
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

   public void clear() {
        this.listaNoticias.clear();
        notifyDataSetChanged();
   }
        public void update(List<Article> resultList) {

                    this.listaNoticias = resultList;
            notifyDataSetChanged();

        }
    }
