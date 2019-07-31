package br.com.dhnews.view;


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

//        // Inicialização do DAO
//        DatabaseRoom databaseRoom = DatabaseRoom.getDatabase(getContext());
//        dao = databaseRoom.noticiasDAO();

        // Add findViewById para recycler
        recyclerView = view.findViewById(R.id.listaNoticiasRecyclerView);

        // Configurar recyclerview e adapater
        noticiasAdapter  = new RecyclerViewNoticiasAdapter(noticiasList);

       // dao = DatabaseRoom.getDatabase(getContext()).noticiasDAO();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(noticiasAdapter);

        viewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);

        viewModel.getNoticias();

        viewModel.getResults().observe(this, results -> noticiasAdapter.update(results));

        return view;
    }



    @Override
    public void onClick(br.com.dhnews.model.Noticias noticias) {

    }

    @Override
    public void onClick(Usuario usuario) {

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
//    public void buscarTodasNoticias() {
//
//        dao.getAllRxJava()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Source>>() {
//                    @Override
//                    public void accept(List<Source> contatos) throws Exception {
//                        adapter.update(noticiasList, noticiasList2);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.i("TAG", "buscarTodosNoticias: " + throwable.getMessage());
//                    }
//                });
//    }
//
//    @Override
//    public void onClick(Noticias noticias) {
//
//        Intent intent = new Intent(getContext(), DetalheNoticiaActivity.class);
//
//        intent.putExtra("NOTICIAS", "DETALHE");
//
//        startActivity(intent);
//
//    }
//
//    @Override
//    public void onClick(Usuario usuario) {
//
//        Intent intentLogin = new Intent(getContext(), MainActivy.class);
//
//        intentLogin.putExtra("TELA", "LOGIN");
//
//        startActivity(intentLogin);
//
//    }


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