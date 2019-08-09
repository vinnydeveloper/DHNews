package br.com.dhnews.view.lerdepois;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.dhnews.R;
import br.com.dhnews.adapters.RecyclerViewLerDepoisAdapter;
import br.com.dhnews.interfaces.RecyclerViewClickListener;
import br.com.dhnews.model.Usuario;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.view.MainActivity;
import br.com.dhnews.view.autenticacao.LoginFragment;
import br.com.dhnews.view.autenticacao.UsuarioFragment;
import br.com.dhnews.view.noticias.DetalheNoticiaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LerDepoisFragment extends Fragment implements RecyclerViewClickListener {

    private ImageButton btnRemoverNoticia;
    RecyclerViewLerDepoisAdapter adapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

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


        recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null ){
            ((MainActivity) getActivity()).replaceFragment(new LoginFragment());
            Toast.makeText(((MainActivity) getActivity()).getApplicationContext(), "Faça login ou se cadastre para ter acesso ao recurso!", Toast.LENGTH_LONG).show();
            return view;

        }
        recyclerViewNoticias.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("noticias").child(user.getUid()).child("items");
        ValueEventListener articletListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                List<Article> noticias = new ArrayList<>();
                for(DataSnapshot templateSnapshot : dataSnapshot.getChildren()){

                    // I've tried a million different things here, but all result in the error
                    // This is the error line
                    Article article = templateSnapshot.getValue(Article.class);
                    noticias.add(article);
                }

                adapter = new RecyclerViewLerDepoisAdapter(noticias);

                recyclerViewNoticias.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerViewNoticias.setAdapter(adapter);
                // ...
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERRO", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(articletListener);


        //btnRemoverNoticia = view.findViewById(R.id.btnBookMarkLerDepois);

        return view;
    }

    //Recebe lista

    @Override
    public void onClick(Article article) {

        Intent intent = new Intent(getContext(), DetalheLerDepoisActivity.class);

        intent.putExtra("NOTICIAS", article);

        startActivity(intent);

    }


    @Override
    public void onClick(Usuario usuario) {

    }
}