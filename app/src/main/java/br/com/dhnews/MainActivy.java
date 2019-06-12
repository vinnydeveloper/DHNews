package br.com.dhnews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.com.dhnews.Noticias.NoticiasActivity;
import br.com.dhnews.home.HomeActivity;
import br.com.dhnews.login.Login;

public class MainActivy extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    replaceFragment(new Login());
                    return true;
                case R.id.navigation_home:
                    replaceFragment(new HomeActivity());
                    return true;
                case R.id.navigation_public:
                    replaceFragment(new NoticiasActivity());
                    return true;
                case R.id.navigation_search:
                    replaceFragment(new Pesquisa());
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activy);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Recebe a flag enviada por outra Activity, para direcionar o fragmento a ser exibido
        String tela = getIntent().getStringExtra("TELA");

        //Valida a flag e o fragmento que precisa ser exibido
        if (tela != null && tela.equals("LOGIN")) {
            replaceFragment(new Login());
        } else if (tela != null && tela.equals("NOTICIA")) {
            replaceFragment(new NoticiasActivity());
        } else {
            replaceFragment(new HomeActivity());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}
