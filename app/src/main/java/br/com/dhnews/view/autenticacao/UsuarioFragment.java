package br.com.dhnews.view.autenticacao;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.dhnews.R;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.view.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioFragment extends Fragment {
    CircleImageView circleImageViewUser;
    TextView nomeCompletoText;
    TextView btnLogout;
    Button btnInteresse;
    CheckBox checkCiencia;
    CheckBox checkTec;
    CheckBox checkEsporte;
    CheckBox checkEntre;
    CheckBox checkSaude;
    CheckBox checkNegocios;


    private FirebaseAuth mAuth;

    public UsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        circleImageViewUser = view.findViewById(R.id.circleImageViewUser);
        nomeCompletoText = view.findViewById(R.id.nomeCompletoText);
        btnLogout = view.findViewById(R.id.btnDesconectarConta);
        btnInteresse = view.findViewById(R.id.btnInteresse);
        checkCiencia = view.findViewById(R.id.checkCiencia);
        checkTec = view.findViewById(R.id.checkTec);
        checkEsporte = view.findViewById(R.id.checkEsporte);
        checkEntre = view.findViewById(R.id.checkEntre);
        checkSaude = view.findViewById(R.id.checkSaude);
        checkNegocios = view.findViewById(R.id.checkNegocios);




        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            nomeCompletoText.setText(user.getDisplayName());

        }

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            ((MainActivity) getActivity()).replaceFragment(new LoginFragment());
        });
        btnInteresse.setOnClickListener(v -> cadastraInteresses());
        return view;
    }

    private void cadastraInteresses() {
        FirebaseUser user = mAuth.getCurrentUser();
        List<String> favoritos = new ArrayList<>();
        if(checkCiencia.isChecked())
            favoritos.add("science");
        if(checkTec.isChecked())
            favoritos.add("technology");
        if(checkEsporte.isChecked())
            favoritos.add("sports");
        if(checkEntre.isChecked())
            favoritos.add("entertainment");
        if(checkSaude.isChecked())
            favoritos.add("health");
        if(checkNegocios.isChecked())
            favoritos.add("business");

        DatabaseReference mfavoritos = FirebaseDatabase.getInstance().getReference()
                .child("noticias")
                .child(user.getUid())
                .child("favoritos");
        mfavoritos.setValue(favoritos);
        Toast.makeText(getContext(), "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();

    }


}
