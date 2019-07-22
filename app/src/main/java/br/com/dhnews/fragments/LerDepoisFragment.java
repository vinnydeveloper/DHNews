package br.com.dhnews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.adapters.NoticiasAdapter;
import br.com.dhnews.data.database.DatabaseRoom;
import br.com.dhnews.data.database.dao.NoticiasDAO;
import br.com.dhnews.view.DetalheNoticiaActivity;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.adapters.RecyclerViewLerDepoisAdapter;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.model.Source;
import br.com.dhnews.model.Usuario;
import br.com.dhnews.view.MainActivy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LerDepoisFragment extends Fragment implements RecyclerViewClickListener {

    private ImageButton btnRemoverNoticia;
    RecyclerViewLerDepoisAdapter adapter;
    private NoticiasDAO dao;
    private List<Article> noticiasList = new ArrayList<>();
    private List<Noticias> noticiasList2 = new ArrayList<>();
    private NoticiasAdapter adapter2;

    public LerDepoisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        // Inicialização do DAO
        DatabaseRoom databaseRoom = DatabaseRoom.getDatabase(getContext());
        dao = databaseRoom.noticiasDAO();

        // Add findViewById para recycler
        RecyclerView recyclerViewNoticias = view.findViewById(R.id.listaNoticiasRecyclerView);

        // Configurar recyclerview e adapater
        NoticiasAdapter adapter2 = new NoticiasAdapter(noticiasList,noticiasList2,this);

        dao = DatabaseRoom.getDatabase(getContext()).noticiasDAO();


        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNoticias.setAdapter(adapter2);

        buscarTodasNoticias();

        return view;
    }

    //    private List<Noticias> getNoticias() {
//
//        List<Noticias> noticias = new ArrayList<>();
//
//        noticias.add(new Noticias("Vaga no Supremo",
//                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
//                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias01));
//
//        noticias.add(new Noticias("Investigação no RJ",
//                "'Querem me atingir', diz Bolsonaro sobre quebra do sigilo de Flávio.",
//                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias02));
//
//        noticias.add(new Noticias("Educação",
//                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
//                "Há 2 horas   —  ", "Educação", R.drawable.imagenoticia03));
//
//        noticias.add(new Noticias("Economia",
//                "Dólar fecha a R$ 4,03 e bolsa atinge menor pontuação do ano.",
//                "Há 5 horas   —  ", "Economia", R.drawable.imagenoticia04));
//
//        noticias.add(new Noticias("Vaga no Supremo",
//                "Bolsonaro nega que tenha feito 'acordo' para indicar Moro ao STF.",
//                "Há 2 horas   —  ", "Política", R.drawable.imagenoticias01));
//
//        noticias.add(new Noticias("Educação",
//                "Presidente do Inep pede demissão após menos de 1 mês no cargo.",
//                "Há 2 horas   —  ", "Educação", R.drawable.imagenoticia03));
//
//        return noticias;
//    }
    public void buscarTodasNoticias() {

        dao.getAllRxJava()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Source>>() {
                    @Override
                    public void accept(List<Source> contatos) throws Exception {
                        adapter2.update(noticiasList, noticiasList2);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("TAG", "buscarTodosNoticias: " + throwable.getMessage());
                    }
                });
    }

    @Override
    public void onClick(Noticias noticias) {

        Intent intent = new Intent(getContext(), DetalheNoticiaActivity.class);

        intent.putExtra("NOTICIAS", "DETALHE");

        startActivity(intent);

    }

    @Override
    public void onClick(Usuario usuario) {

        Intent intentLogin = new Intent(getContext(), MainActivy.class);

        intentLogin.putExtra("TELA", "LOGIN");

        startActivity(intentLogin);

    }
}