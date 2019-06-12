package com.example.myapplication;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    public ImageView circleImage;
    private View mView;
    private TextView nomeCompletoText;
    private TextView interessesText;
    private CheckBox checkPoliticas;
    private CheckBox checkEntre;
    private CheckBox checkEsporte;
    private CheckBox checkModa;
    private CheckBox checkCulinaria;
    private CheckBox checkTec;
    private Button btnDesconectar;
    private Button btnAlertaManter;
    private Button btnAlertaSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnDesconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

    }

    public void initViews() {
        circleImage = findViewById(R.id.circleImageView);
        checkCulinaria = findViewById(R.id.checkCulinaria);
        btnDesconectar = findViewById(R.id.btnDesconectarConta);
    }

    public void dialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Desconectar Conta?");
        alertDialog.setMessage("");
        alertDialog.setCancelable(false);
        alertDialog.setNegativeButton("MANTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setPositiveButton("DESCONECTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager fm = getSupportFragmentManager();
                BlankFragment fragment = new BlankFragment();
                fm.beginTransaction().add(R.id.containerr, fragment).commit();

            }
        });
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

}
