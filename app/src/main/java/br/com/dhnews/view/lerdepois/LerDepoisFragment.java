package br.com.dhnews.view.lerdepois;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
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
import br.com.dhnews.model.noticias.Noticias;

/**
 * A simple {@link Fragment} subclass.
 */
public class LerDepoisFragment extends Fragment implements RecyclerViewClickListener {

    private ImageButton btnRemoverNoticia;
    private RecyclerViewLerDepoisAdapter adapter;
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
        recyclerViewNoticias.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("noticias").child(user.getUid()).child("items");
        ValueEventListener articletListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                List<Article> noticias = new ArrayList<>();
                for (DataSnapshot templateSnapshot : dataSnapshot.getChildren()) {

                    // I've tried a million different things here, but all result in the error
                    // This is the error line
                    Article article = templateSnapshot.getValue(Article.class);
                    noticias.add(article);
                }

                adapter.update(noticias);
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

    }

    @Override
    public void onClick(br.com.dhnews.model.Article article) {

    }

    @Override
    public void onClick(Usuario usuario) {

    }

    //Recebe lista


//    public void removeFavoriteClickListener(Noticias result) {
//
//        // Pegamos a instancia do firebase, objeto necessario para salvar no banco de dados
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//        // pegamos a referencia para onde no firebase queremos salvar nossos dados
//        DatabaseReference reference = database.getReference("favorites");
//
//        // Adicionamos o listener par pegar os resultados do firebase
//        reference.orderByChild("id").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                // Quando retornar algo do firebase percorremos os dados e colocamos na lista
//                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
//                    Noticias resultFirebase = resultSnapshot.getValue(Noticias.class);
//
//                    // Se acho o mesmo id removemos o item
//                    if (result.getId().equals(resultFirebase.getId())) {
//                        resultSnapshot.getRef().removeValue();
//                        adapter.removeItem(result);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}


