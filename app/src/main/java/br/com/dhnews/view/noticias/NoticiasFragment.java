package br.com.dhnews.view.noticias;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.adapters.RecyclerViewNoticiasAdapter;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.model.Usuario;
import br.com.dhnews.viewmodel.NoticiasViewModel;

import static br.com.dhnews.util.AppUtil.isNetworkConnected;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment implements RecyclerViewClickListener {

    private List<Article> noticiasList = new ArrayList<>();
    private RecyclerViewNoticiasAdapter noticiasAdapter;
    private RecyclerView recyclerView;
    private NoticiasViewModel viewModel;



    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        recyclerView = view.findViewById(R.id.listaNoticiasRecyclerView);

        noticiasAdapter  = new RecyclerViewNoticiasAdapter(noticiasList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(noticiasAdapter);

        viewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);

        if (isNetworkConnected(getContext())) {
            viewModel.getNoticias();
        } else {
            viewModel.getFromLocal();
        }

        viewModel.getResults().observe(this, results -> noticiasAdapter.update(results));

        return view;
    }



    @Override
    public void onClick(Article article) {

    }

    @Override
    public void onClick(br.com.dhnews.model.Article article) {
    }

    @Override
    public void onClick(Usuario usuario) {

    }
    private void setScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = manager.getItemCount();
                int lastVisible = manager.findLastVisibleItemPosition();

                boolean fimFoiEncontrado = lastVisible + 5 >= totalItemCount;

//                if (totalItemCount > 0 && fimFoiEncontrado) {
//                    pagina++;
//                    viewModel.searchItem(itemBusca, pagina, limite);
//                }
            }
        });
    }
}