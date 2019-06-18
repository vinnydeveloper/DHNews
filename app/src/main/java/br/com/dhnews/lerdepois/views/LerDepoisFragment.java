package br.com.dhnews.lerdepois.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.detalhenoticia.DetalheNoticiaActivity;
import br.com.dhnews.lerdepois.adapters.RecyclerViewLerDepoisAdapter;
import br.com.dhnews.lerdepois.interfaces.RecyclerViewClickListener;
import br.com.dhnews.lerdepois.model.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class LerDepoisFragment extends Fragment implements RecyclerViewClickListener {

    private ImageButton btnRemoverNoticia;
    RecyclerViewLerDepoisAdapter adapter;

    public LerDepoisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_ler_depois, container, false);
// Add findViewById para recycler
        RecyclerView recyclerViewNoticias = view.findViewById(R.id.recyclerViewMark);
// Configurar recyclerview e adapater
        adapter = new RecyclerViewLerDepoisAdapter(getNoticias(), this);

        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNoticias.setAdapter(adapter);
        btnRemoverNoticia = view.findViewById(R.id.btnBookMarkLerDepois);
        return view;
    }

    //Recebe lista
    private List<Noticia> getNoticias() {
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia("Titulo da Noticia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in volupta", R.drawable.sergiomoro, "Política", "Há 2 horas"));
        noticias.add(new Noticia("Outra Notícia", "Lorem ipsum dolor sit amet, consectetur adi", R.drawable.educacao, "Educação", "Há 1 hora"));
        return noticias;
    }

    //vai para o detalhe da noticia
    @Override
    public void onClick(Noticia noticia) {

        Intent intent = new Intent(getContext(), DetalheNoticiaActivity.class);
        startActivity(intent);
    }



}