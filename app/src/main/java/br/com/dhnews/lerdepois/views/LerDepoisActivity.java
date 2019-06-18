package br.com.dhnews.lerdepois.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import br.com.dhnews.R;

public class LerDepoisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_depois);

        replaceFragment(new LerDepoisFragment());

    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerLerDepois, fragment)
                .commit();
    }
}