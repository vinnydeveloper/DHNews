package br.com.dhnews.view.home;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.adapters.RecyclerViewLerDepoisAdapter;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.R;
import br.com.dhnews.adapters.RecyclerViewNoticiasAdapter;
import br.com.dhnews.model.Usuario;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.view.MainActivity;
import br.com.dhnews.view.autenticacao.LoginFragment;
import br.com.dhnews.view.noticias.DetalheNoticiaActivity;
import br.com.dhnews.viewmodel.NoticiasViewModel;

import static br.com.dhnews.util.AppUtil.isNetworkConnected;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RecyclerViewClickListener {
    private List<Article> noticiasList = new ArrayList<>();
    RecyclerViewNoticiasAdapter adapter;
    private NoticiasViewModel viewModel;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private List<String> favoritos;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Add findViewById para recycler
        RecyclerView recyclerViewNoticias = view.findViewById(R.id.listaNoticiasRecyclerView);

        // Configurar recyclerview e adapater

        adapter = new RecyclerViewNoticiasAdapter(noticiasList);

        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNoticias.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null ){
            ((MainActivity) getActivity()).replaceFragment(new LoginFragment());
            Toast.makeText(((MainActivity) getActivity()).getApplicationContext(), "Faça login ou se cadastre para ter acesso ao recurso!", Toast.LENGTH_LONG).show();
            return view;

        }


        mDatabase = FirebaseDatabase.getInstance().getReference().child("noticias").child(user.getUid()).child("favoritos");
        ValueEventListener articletListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                List<Article> noticias = new ArrayList<>();
                for(DataSnapshot templateSnapshot : dataSnapshot.getChildren()) {

                    // I've tried a million different things here, but all result in the error
                    // This is the error line
                    String item = templateSnapshot.getValue(String.class);

                    viewModel.getNoticiasFavoritas(item, 2);
                    viewModel.getResults().observe(getViewLifecycleOwner(), results -> adapter.update(results));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERRO", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(articletListener);

        return view;
    }




    @Override
    public void onClick(Article article) {

        Intent intent = new Intent(getContext(), DetalheNoticiaActivity.class);

        intent.putExtra("NOTICIAS", article);

        startActivity(intent);

    }

    @Override
    public void onClick(Usuario usuario) {

        Intent intentLogin = new Intent(getContext(), MainActivity.class);

        intentLogin.putExtra("TELA", "LOGIN");

        startActivity(intentLogin);

    }

}