package br.com.dhnews.view.Noticias;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import br.com.dhnews.model.Noticias;
import br.com.dhnews.R;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.view.MainActivity;

import static br.com.dhnews.util.AppUtil.formatarData;

public class DetalheNoticiaActivity extends AppCompatActivity {

    //Declaracao de atributos
    private TextView textViewTituloDetalheNoticia;
    private TextView textViewHorarioDetalheNoticia;
    private TextView textViewFonteDetalheNoticia;
    private TextView textViewConteudoDetalheNoticia;
    private ImageView imageViewBackDetalheNoticia;
    private ImageView imageViewShareDetalheNoticia;
    private ImageView imageViewBookMarkDetalheNoticia;
    private ImageView imageViewDetalheNoticia;
    private AppBarLayout appBarLayout;
    private Noticias noticias;
    private Toolbar toolbar;
    private View textViewFonteNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);


        //Metodo para inicializar as Views
        initViews();
        setSupportActionBar(toolbar);

        //Valido se veio algum dado na intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            Article noticias = getIntent().getParcelableExtra("NOTICIAS");

            if (noticias != null) {

                retornaDetalheListaNoticias(noticias);

                //Metodo para voltar para a tela com a Lista de noticias
                chamaListaNoticia();

                //Metodo para acessar os aplicativos de compartilhamento de dados
                compartilharNoticia();


                //Metodo para chamar a tela de LoginFragment para cadastrar da opcao ler noticia depois
                cadastraLerDepois();
            }
        }
    }

  //  @SuppressLint("WrongViewCast")
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        imageViewBackDetalheNoticia = findViewById(R.id.imageBack);
        imageViewDetalheNoticia = findViewById(R.id.imagemNoticiaDetalhe);
        appBarLayout = findViewById(R.id.appBar);
        textViewConteudoDetalheNoticia = findViewById(R.id.textViewConteudoNoticiaDetalhe);


        textViewTituloDetalheNoticia = findViewById(R.id.textViewTituloNoticiaDetalhe);
      textViewHorarioDetalheNoticia = findViewById(R.id.textViewHorarioNoticiaDetalhe);
     textViewFonteDetalheNoticia = findViewById(R.id.textViewFonteNoticiaDetalhe);
       imageViewShareDetalheNoticia = findViewById(R.id.imagemShareNoticiaDetalhe);
      imageViewBookMarkDetalheNoticia = findViewById(R.id.imagemBookMarkNoticiaDetalhe);
    }

    private void retornaDetalheListaNoticias(Article noticias) {

       textViewTituloDetalheNoticia.setText(noticias.getTitle());


        textViewHorarioDetalheNoticia.setText(formatarData(noticias.getPublishedAt()));



        // textViewFonteNoticia.setText(noticias.getSource());
        textViewConteudoDetalheNoticia.setText(noticias.getContent());

     //   toolbar.setTitle(noticias.getTitle());

        if (noticias.getUrlToImage() != null) {

            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get()
                    .load(noticias.getUrlToImage())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageViewDetalheNoticia);
        }



    }


    private void chamaListaNoticia() {
        imageViewBackDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela com a Lista de Noticias

                //Chama a Main Activity que verifica qual opcao do Menu Principal foi acionado para
                //chamar a tela/fragmento correspondente
                Intent intentListaNoticias = new Intent(
                        DetalheNoticiaActivity.this, MainActivity.class);

                //Chama o fragmento da tela de Noticias(atraves de um flag 'Tela' com valor
                //'Noticia')  para retornar para a lista de noticias
                intentListaNoticias.putExtra("TELA", "NOTICIA");

                startActivity(intentListaNoticias);
            }
        });
    }

    private void compartilharNoticia() {
        imageViewShareDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(Intent.EXTRA_TEXT, "Compartilhando noticias");

                //tipo de compartilhamento
                intentCompartilhar.setType("text/plain");

                //Mostra os aplicativos disponiveis para compartilhamento de dados
                Intent intentChooser = Intent.createChooser(
                        intentCompartilhar, "Compartilhar via:");

                //Start na Activity de compartilhamento
                startActivity(intentChooser);
            }
        });
    }

    private void cadastraLerDepois() {
        imageViewBookMarkDetalheNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a Main Activity que verifica qual opcao do Menu Principal foi acionado para
                //chamar a tela/fragmento correspondente
                Intent intentLerDepois = new Intent(
                        DetalheNoticiaActivity.this, MainActivity.class);

                //Chama o fragmento da tela de LoginFragment(atraves de um flag 'Tela' com valor 'LoginFragment')
                //para cadastrar o ler noticia depois
                intentLerDepois.putExtra("TELA", "LOGIN");

                startActivity(intentLerDepois);
            }
        });
    }
}