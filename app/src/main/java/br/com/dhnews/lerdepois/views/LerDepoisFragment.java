package br.com.dhnews.lerdepois.views;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.lerdepois.adapters.RecyclerViewLerDepoisAdapter;
import br.com.dhnews.lerdepois.detalhe.DetalheLerDepoisActivity;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Usuario;

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
    private List<Noticias> getNoticias() {

        List<Noticias> noticias = new ArrayList<>();

        noticias.add(new Noticias("Vaga no Supremo",
                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias01));

        noticias.add(new Noticias("Investigação no RJ",
                "'Querem me atingir', diz Bolsonaro sobre quebra do sigilo de Flávio.",
                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias02));

        noticias.add(new Noticias("Educação",
                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
                "Há 2 horas   —  ", "Educação", R.drawable.imagenoticia03));

        noticias.add(new Noticias("Economia",
                "Dólar fecha a R$ 4,03 e bolsa atinge menor pontuação do ano.",
                "Há 5 horas   —  ", "Economia", R.drawable.imagenoticia04));

        noticias.add(new Noticias("Vaga no Supremo",
                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias01));

        noticias.add(new Noticias("Educação",
                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
                "Há 2 horas   —  ", "Educação", R.drawable.imagenoticia03));

        return noticias;
    }

    @Override
    public void onClick(Noticias noticias) {

        Intent intent = new Intent(getContext(), DetalheLerDepoisActivity.class);

        intent.putExtra("NOTICIAS", noticias);

        startActivity(intent);

    }

    @Override
    public void onClick(Usuario usuario) {

    }
}