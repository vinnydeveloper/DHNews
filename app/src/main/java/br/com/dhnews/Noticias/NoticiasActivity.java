package br.com.dhnews.Noticias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.adapters.NoticiasAdapter;
import br.com.dhnews.model.Noticias;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasActivity extends Fragment {

    RecyclerView recyclerViewNoticias;

    NoticiasAdapter adapter;


    public NoticiasActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerViewNoticias = findViewById(R.id.listaNoticiasRecyclerView);
//
//        adapter = new NoticiasAdapter(listaNoticias());
//
//        recyclerViewNoticias.setAdapter(adapter);
//
//        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(this));
//    }
//


    private List<Noticias> listaNoticias(){
        List<Noticias> noticias = new ArrayList<>();


        noticias.add(new Noticias("Titulo da noticia 1", "Aqui fica a descrição da notícia"));
        noticias.add(new Noticias("Titulo da noticia 2", "Aqui fica a descrição da notícia"));
        noticias.add(new Noticias("Titulo da noticia 3", "Aqui fica a descrição da notícia"));

        return noticias;
    }
}
