
package br.com.dhnews.view.Autenticação;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.com.dhnews.R;
import br.com.dhnews.view.Home.HomeFragment;
import br.com.dhnews.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {

    private FirebaseAuth mAuth;

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
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

                    mAuth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(nomeCadastro)
                                                .build();
                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            ((MainActivity) getActivity()).replaceFragment(new NoticiasFragment());
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(),
                                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
            }
        });
    }

}