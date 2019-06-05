package br.com.dhnews.detalhenoticia;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dhnews.MainActivy;
import br.com.dhnews.R;
import br.com.dhnews.login.Login;

public class DetalheNoticiaActivity extends AppCompatActivity {

    //Declaração de atributos
    private TextView textViewTituloDetalheNoticia;
    private TextView textViewSubTituloDetalheNoticia;
    private TextView textViewHorarioDetalheNoticia;
    private TextView textViewConteudoDetalheNoticia;
    private ImageView imageViewBackDetalheNoticia;
    private ImageView imageViewShareDetalheNoticia;
    private ImageView imageViewBookMarkDetalheNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        //Metodo para inicializar as View's
        initViews();

        //*******************************ATENÇÃO****************************************************
        //Este Metodo é temporario, pois os dados serão alimentados pela tela anterior que Lista as
        // noticias
        textViewTituloDetalheNoticia.setText("Vaga no Supremo");

        textViewSubTituloDetalheNoticia.setText("Bolsonaro nega que tenha feito 'acordo' para" +
                "\nindicar Moro ao STF");

        textViewHorarioDetalheNoticia.setText("Há 2 horas   —  Política");

        textViewConteudoDetalheNoticia.setText("Lorem ipsum dolor sit amet, consectetur " +
                "\nadipiscing elit, sed do eiusmod tempor " +
                "\nincididunt ut labore et dolore magna aliqua.\n" +
                " \n" +
                "Ut enim ad minim veniam, quis nostrud " +
                "\nexercitation ullamco laboris nisi ut aliquip" +
                "\nex ea commodo consequat. \n" +
                "\n" +
                "Duis aute irure dolor in reprehenderit in " +
                "\nvoluptate velit esse cillum dolore eu fugiat " +
                "\nnulla pariatur. \n" +
                "\n" +
                "Excepteur sint occaecat cupidatat non " +
                "\nproident, sunt in culpa qui officia deserunt " +
                "\nmollit anim id est laborum.");
        //*******************Atenção****************************************************************

        //Metodo para voltar para a tela com a Lista de noticias
        chamaListaNoticia();

        //Metodo para acessar os aplicativos de compartilhamento de dados
        compartilharNoticia();

        //Metodo para chamar a tela de Login para cadastrar da opção ler noticia depois
        cadastraLerDepois();
    }

    private void initViews() {
        textViewTituloDetalheNoticia = findViewById(R.id.textViewTituloNoticiaDetalhe);
        textViewSubTituloDetalheNoticia = findViewById(R.id.textViewSubTituloNoticiaDetalhe);
        textViewHorarioDetalheNoticia = findViewById(R.id.textViewHorarioNoticiaDetalhe);
        textViewConteudoDetalheNoticia = findViewById(R.id.textViewConteudoNoticiaDetalhe);
        imageViewBackDetalheNoticia = findViewById(R.id.imagemBackNoticiaDetalhe);
        imageViewShareDetalheNoticia = findViewById(R.id.imagemShareNoticiaDetalhe);
        imageViewBookMarkDetalheNoticia = findViewById(R.id.imagemBookMarkNoticiaDetalhe);
    }

    private void chamaListaNoticia() {
        imageViewBackDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela com a Lista de Noticias

                //Pendente nome da tela de lista de noticia para comunicação ????
                Intent intentListaNoticias = new Intent(
                        DetalheNoticiaActivity.this,
                        MainActivy.class);

                startActivity(intentListaNoticias);
            }
        });
    }

    private void compartilharNoticia() {
        imageViewShareDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Compartilhar via");
                intentCompartilhar.setType("text/plain");

                //Exibe os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar,
                        "Compartilhar via:");

                startActivity(intentChooser);
            }
        });
    }

    private void cadastraLerDepois() {
        imageViewBookMarkDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela de Login para cadastrar o ler noticia depois
                //Intent intentLerDepois = new Intent(DetalheNoticiaActivity.this, Login.class);

                //Chama a Main Activity que verifica qual icone do Menu foi acionado para chamar a
                // tela correspondente
                Intent intentLerDepois = new Intent(
                        DetalheNoticiaActivity.this,
                        MainActivy.class);

                startActivity(intentLerDepois);
            }
        });
    }
}