package br.com.dhnews.view.Autenticação;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.dhnews.R;
import br.com.dhnews.view.Autenticação.CadastroFragment;
import br.com.dhnews.view.MainActivity;
import br.com.dhnews.view.Noticias.NoticiasFragment;
import br.com.dhnews.view.UsuarioActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnFacebook = view.findViewById(R.id.btnFacebook);
        Button btnGoogle = view.findViewById(R.id.btnGoogle);
        Button btnCadastra = view.findViewById(R.id.btnCadastrar);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        //Declaracao de atributos para validacao do preenchimento de dados da Tela de LoginFragment
        final TextInputLayout textInputLayoutLogEmail = view.findViewById(
                R.id.textInputLayoutEmail);

        final TextInputLayout textInputLayoutLogPassword = view.findViewById(
                R.id.textInputLayoutPassword);
        final SharedPreferences preferences = getContext().getSharedPreferences("APP", Context.MODE_PRIVATE);
        textInputLayoutLogEmail.getEditText().setText(preferences.getString("EMAIL", ""));
        textInputLayoutLogPassword.getEditText().setText(preferences.getString("SENHA", ""));


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Valida o prenchimento dos dados da Tela de LoginFragment
                String emailLog = textInputLayoutLogEmail.getEditText().getText().toString();
                String senhaLog = textInputLayoutLogPassword.getEditText().getText().toString();

                //Inicializa o set Error
                textInputLayoutLogEmail.setError("");
                textInputLayoutLogPassword.setError("");

                if (emailLog.isEmpty()) {
                    textInputLayoutLogEmail.setError("Informe seu e-mail");
                    return;
                }

                if (senhaLog.isEmpty()) {
                    textInputLayoutLogPassword.setError("Informe sua senha");
                    return;
                }

                if (!(emailLog.isEmpty()) && !(senhaLog.isEmpty())) {

                    SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
                    preferences.edit().putString("EMAIL", emailLog).commit();
                    preferences.edit().putString("SENHA", senhaLog).commit();
                    //se preenchido automaticamente, vai pra tela usuario
                    Intent intent = new Intent(getContext(), UsuarioActivity.class);
                    startActivity(intent);

                    ((MainActivity) getActivity()).replaceFragment(new NoticiasFragment());

                }
            }
        });

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).replaceFragment(new CadastroFragment());
            }
        });


    } 
}