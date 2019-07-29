package br.com.dhnews.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dhnews.R;

public class UsuarioActivity extends AppCompatActivity {

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
    private Button btnDesconectarConta;
    private ImageView btnBackUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        initViews();
        btnDesconectarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        btnBackUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoVoltar();
            }
        });


    }

    public void initViews() {
        circleImage = findViewById(R.id.circleImageView);
        checkCulinaria = findViewById(R.id.checkCulinaria);
        btnDesconectarConta = findViewById(R.id.btnDesconectarConta);
        btnBackUser = findViewById(R.id.imagemBackUser);
    }


    //Caixa de dialogo
    public void dialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UsuarioActivity.this);
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

                //Ao clicar em Desconectar, vai para Home(main activity)
                Intent intent = new Intent(UsuarioActivity.this, MainActivy.class);
                startActivity(intent);

            }
        });
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

    public void botaoVoltar() {
        Intent intent = new Intent(UsuarioActivity.this, MainActivy.class);

        startActivity(intent);
    }
}