package br.com.dhnews.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.model.Noticias;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private List<Noticias> listaNoticias;

    public NoticiasAdapter(List<Noticias> listaContatos) {
        this.listaNoticias = listaContatos;
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
        Noticias novaNoticia = listaNoticias.get(position);

        viewHolder.bind(novaNoticia);
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tituloNoticia;
        TextView descricaoNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloNoticia = itemView.findViewById(R.id.txtTitulo);
            descricaoNoticia = itemView.findViewById(R.id.txtDescricao);
        }

        public void bind(Noticias noticia) {
            tituloNoticia.setText(noticia.getTituloNoticia());
            descricaoNoticia.setText(noticia.getDescricaoNoticia());
        }
    }
}
