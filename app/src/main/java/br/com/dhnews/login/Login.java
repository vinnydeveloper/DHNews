package br.com.dhnews.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.dhnews.MainActivy;
import br.com.dhnews.Noticias.NoticiasActivity;
import br.com.dhnews.R;
import br.com.dhnews.cadastro.Cadastro;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    public Login() {
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

        Button btnCadastra = view.findViewById(R.id.btnCadastrar);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        //Declaracao de atributos para validacao do preenchimento de dados da Tela de Login
        final TextInputLayout textInputLayoutLogEmail = view.findViewById(
                R.id.textInputLayoutEmail);

        final TextInputLayout textInputLayoutLogPassword = view.findViewById(
                R.id.textInputLayoutPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Valida o prenchimento dos dados da Tela de Login
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

                //Se todos os campos estiverem preenchidos chama a tela de Home
                if (!(emailLog.isEmpty()) && !(senhaLog.isEmpty())) {

                    ((MainActivy)getActivity()).replaceFragment(new NoticiasActivity());

                }
            }
        });

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivy) getActivity()).replaceFragment(new Cadastro());
            }
        });
    }
}
