package br.com.dhnews.view;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dhnews.R;
import br.com.dhnews.view.Autenticação.LoginFragment;
import br.com.dhnews.view.Favoritos.FavoritosFragment;
import br.com.dhnews.view.Home.HomeFragment;
import br.com.dhnews.view.Noticias.NoticiasFragment;
import br.com.dhnews.view.Pesquisa.PesquisaFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {

                    case R.id.navigation_mark:
                        replaceFragment(new FavoritosFragment());
                        return true;
                    case R.id.navigation_user:
                        replaceFragment(new LoginFragment());
                        return true;
                    case R.id.navigation_home:
                        replaceFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_public:
                        replaceFragment(new NoticiasFragment());
                        return true;
                    case R.id.navigation_search:
                        replaceFragment(new PesquisaFragment());
                        return true;

                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Recebe a flag enviada por outra Activity, para direcionar o fragmento a ser exibido
        String tela = getIntent().getStringExtra("TELA");

        //Valida a flag e o fragmento que precisa ser exibido
        if (tela != null && tela.equals("LOGIN")) {
            replaceFragment(new LoginFragment());
        } else if (tela != null && tela.equals("NOTICIA")) {
            replaceFragment(new NoticiasFragment());
        } else if (tela != null && tela.equals("LERDEPOIS")) {
            replaceFragment(new FavoritosFragment());
        } else {
            replaceFragment(new NoticiasFragment());

        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}