
package br.com.dhnews.cadastro;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.dhnews.view.MainActivy;
import br.com.dhnews.R;
import br.com.dhnews.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {


    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnFinalizarCadastro = view.findViewById(R.id.btnSing);

        //Declaracao de atributos para validacao do preenchimento de dados da Tela de CadastroFragment
        final TextInputLayout textInputLayoutCadNome = view.findViewById(
                R.id.TextInputNome);

        final TextInputLayout textInputLayoutCadEmail = view.findViewById(
                R.id.TextInputEmail);

        final TextInputLayout textInputLayoutCadSenha = view.findViewById(
                R.id.TextInputPassword);

        btnFinalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Valida o prenchimento dos dados da Tela de CadastroFragment
                String nomeCadastro = textInputLayoutCadNome.getEditText().getText().toString();
                String emailCadastro = textInputLayoutCadEmail.getEditText().getText().toString();
                String senhaCadastro =
                        textInputLayoutCadSenha.getEditText().getText().toString();

                //minimo de caracteres permitidos para o cadastro de senha
                int minimalPassLen = 6;

                //Inicializa o set Error
                textInputLayoutCadNome.setError("");
                textInputLayoutCadEmail.setError("");
                textInputLayoutCadSenha.setError("");

                if (nomeCadastro.isEmpty()) {
                    textInputLayoutCadNome.setError("Informe seu nome");
                    return;
                }

                if (emailCadastro.isEmpty()) {
                    textInputLayoutCadEmail.setError("Informe seu e-mail");
                    return;
                }

                if (senhaCadastro.isEmpty()) {
                    textInputLayoutCadSenha.setError("Informe sua senha");
                    return;
                }

                if (senhaCadastro.length() < minimalPassLen) {
                    textInputLayoutCadSenha.setError("Digite a senha com 6 ou mais caracteres");
                    return;
                }

                //Se todos os campos estiverem preenchidos chama a tela de Home
                if (!(nomeCadastro.isEmpty()) && !(emailCadastro.isEmpty()) &&
                        !(senhaCadastro.isEmpty())) {

                    ((MainActivy) getActivity()).replaceFragment(new HomeFragment());

                }
            }
        });
    }
}