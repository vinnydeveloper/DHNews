package br.com.dhnews.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.adapters.RecyclerViewNoticiasAdapter;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.viewmodel.NoticiasViewModel;

public class PesquisaFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView editTextSearch;
    private RecyclerViewNoticiasAdapter adapter;
    private List<Article> results = new ArrayList<>();
    private NoticiasViewModel viewModel;

    private int limite = 10;
    private String itemBusca;

    public PesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        editTextSearch = view.findViewById(R.id.editPesquisa);
        viewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);
        adapter = new RecyclerViewNoticiasAdapter(results);
        editTextSearch.requestFocus();

        InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editTextSearch, InputMethodManager.SHOW_IMPLICIT);

      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
              recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);

        viewModel.searchItem(itemBusca, limite);

        viewModel.getResults().observe(this, results -> adapter.update(results));
        setScrollListener();


        editTextSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                itemBusca = query;
                adapter.clear();
                viewModel.searchItem(itemBusca, limite);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 3){
                    itemBusca = newText;
                    adapter.clear();
                    viewModel.searchItem(itemBusca, limite);
                }
                return false;
            }
        });
        return view;
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

            boolean fimEncontrado = lastVisible + 5 >= totalItemCount;

            if (totalItemCount > 0 && fimEncontrado) {
                limite++;
                viewModel.searchItem(itemBusca, limite);
            }

            }
        });
    }



//        String[] arrayList = new String[]{"Esporte", "Politica", "Cinema", "Tecnologia"};
//        final ArrayAdapter<String> adapter;
//
//
//        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newQuery) {
//
//                adapter.getFilter().filter(newQuery);
//
//                return false;
//            }
//        });



}