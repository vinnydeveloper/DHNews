package br.com.dhnews.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dhnews.R;
import br.com.dhnews.fragments.LerDepoisFragment;

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