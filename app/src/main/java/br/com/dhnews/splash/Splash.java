package br.com.dhnews.splash;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import br.com.dhnews.MainActivy;
import br.com.dhnews.R;
import br.com.dhnews.login.Login;

public class Splash extends AppCompatActivity {

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView imageViewLogo = findViewById(R.id.imageViewLogo);

        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump();
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump();
            }
        }, 3000);
    }

    private void jump() {
        timer.cancel();
        Intent intent = new Intent(Splash.this, MainActivy.class);
        startActivity(intent);
        finish();
    }
}
