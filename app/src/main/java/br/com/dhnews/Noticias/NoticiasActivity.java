package br.com.dhnews.Noticias;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.Interface.RecyclerViewClickListener;
import br.com.dhnews.MainActivy;
import br.com.dhnews.R;
import br.com.dhnews.adapters.NoticiasAdapter;
import br.com.dhnews.detalhenoticia.DetalheNoticiaActivity;
import br.com.dhnews.model.Noticias;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasActivity extends Fragment  implements RecyclerViewClickListener {

    public NoticiasActivity() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
        // Add findViewById para recycler
        RecyclerView recyclerViewNoticias = view.findViewById(R.id.listaNoticiasRecyclerView);

        // Configurar recyclerview e adapater
        NoticiasAdapter adapter = new NoticiasAdapter(getNoticias(), this);

        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNoticias.setAdapter(adapter);

        return view;
    }

    private List<Noticias> getNoticias() {

        List<Noticias> noticias = new ArrayList<>();

        noticias.add(new Noticias("Vaga no Supremo",
                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
                "Há 2 horas   —  Política",R.drawable.imagenoticias01));

        noticias.add(new Noticias("Investigação no RJ",
                "'Querem me atingir', diz Bolsonaro sobre quebra do sigilo de Flávio",
                "Há 2 horas   —  Política",R.drawable.imagenoticias02));

        noticias.add(new Noticias("Educação",
                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
                "Há 2 horas   —   Educação",R.drawable.imagenoticia03));

        noticias.add(new Noticias("Economia",
                "Dólar fecha a R$ 4,03 e bolsa atinge menor pontuação do ano.",
                "Há 5 horas   —  Economia",R.drawable.imagenoticia04));

        noticias.add(new Noticias("Vaga no Supremo",
                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
                "Há 2 horas   —  Política",R.drawable.imagenoticias01));

        noticias.add(new Noticias("Educação",
                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
                "Há 2 horas   —   Educação",R.drawable.imagenoticia03));


        return noticias;
    }

    @Override
    public void onClick(Noticias noticias) {

        Intent intent = new Intent(getContext(), DetalheNoticiaActivity.class);
        startActivity(intent);

    }

}