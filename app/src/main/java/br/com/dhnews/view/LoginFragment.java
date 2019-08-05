package br.com.dhnews.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.dhnews.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        if(verificaUserLogado()){
            ((MainActivity) getActivity()).replaceFragment(new UsuarioFragment());
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = GoogleSignIn.getClient(getActivity(), gso);
        mAuth = FirebaseAuth.getInstance();

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
//                    Intent intent = new Intent(getContext(), UsuarioActivity.class);
//                    startActivity(intent);
                    mAuth.signInWithEmailAndPassword(emailLog, senhaLog)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        ((MainActivity) getActivity()).replaceFragment(new UsuarioFragment());

                                    } else {
                                        Toast.makeText( ((MainActivity) getActivity()).getApplicationContext(),
                                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
            }
        });

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).replaceFragment(new CadastroFragment());
            }
        });

        btnGoogle.setOnClickListener(v->signIn());


    }

    private boolean verificaUserLogado(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        return user != null;
    }
    private void signIn() {
//        Intent signInIntent = mGoogleApiClient.getSignInIntent();
//        (signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                authWithGoogle(account);
            }
        }
    }
    private void authWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ((MainActivity) getActivity()).replaceFragment(new UsuarioFragment());
                }
                else{
                    Toast.makeText(getContext(),"Auth Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(),"Falha na conexão",Toast.LENGTH_SHORT).show();
    }
}