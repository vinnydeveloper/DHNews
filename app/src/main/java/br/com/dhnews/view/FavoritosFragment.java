package br.com.dhnews.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.dhnews.R;
import br.com.dhnews.adapters.FavoritosAdapter;
import br.com.dhnews.adapters.RecyclerViewNoticiasAdapter;


public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerViewFavoritos;
    private FavoritosAdapter adapter;


    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ler_depois, container, false);

        recyclerViewFavoritos = view.findViewById(R.id.recyclerViewMark);
        recyclerViewFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
       // adapter = new FavoritosAdapter(new ArrayList<>(), this);
        recyclerViewFavoritos.setAdapter(adapter);

        return view;
    }

}
