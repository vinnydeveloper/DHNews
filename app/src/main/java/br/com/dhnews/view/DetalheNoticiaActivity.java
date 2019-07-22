package br.com.dhnews.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.R;

public class DetalheNoticiaActivity extends AppCompatActivity {

    //Declaracao de atributos
    private TextView textViewTituloDetalheNoticia;
    private TextView textViewSubTituloDetalheNoticia;
    private TextView textViewHorarioDetalheNoticia;
    private TextView textViewAssuntoDetalheNoticia;
    private TextView textViewConteudoDetalheNoticia;
    private ImageView imageViewBackDetalheNoticia;
    private ImageView imageViewShareDetalheNoticia;
    private ImageView imageViewBookMarkDetalheNoticia;
    private ImageView imageViewFotoDetalheNoticia;
    private Article not;
    private Noticias not2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        //Metodo para inicializar as Views
        initViews();

        //Valido se veio algum dado na intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            Noticias noticias = getIntent().getParcelableExtra("NOTICIAS");

            if (noticias != null) {

                retornaDetalheListaNoticias(not,not2);

                //Metodo para voltar para a tela com a Lista de noticias
                chamaListaNoticia();

                //Metodo para acessar os aplicativos de compartilhamento de dados
                compartilharNoticia();


                //Metodo para chamar a tela de LoginFragment para cadastrar da opcao ler noticia depois
                cadastraLerDepois();
            }
        }
    }

    private void initViews() {
        textViewTituloDetalheNoticia = findViewById(R.id.textViewTituloNoticiaDetalhe);
        textViewSubTituloDetalheNoticia = findViewById(R.id.textViewSubTituloNoticiaDetalhe);
        textViewHorarioDetalheNoticia = findViewById(R.id.textViewHorarioNoticiaDetalhe);
        textViewAssuntoDetalheNoticia = findViewById(R.id.textViewAssuntoNoticiaDetalhe);
        textViewConteudoDetalheNoticia = findViewById(R.id.textViewConteudoNoticiaDetalhe);
        imageViewBackDetalheNoticia = findViewById(R.id.imagemBackNoticiaDetalhe);
        imageViewShareDetalheNoticia = findViewById(R.id.imagemShareNoticiaDetalhe);
        imageViewBookMarkDetalheNoticia = findViewById(R.id.imagemBookMarkNoticiaDetalhe);
        imageViewFotoDetalheNoticia = findViewById(R.id.imagemNoticiaDetalhe);
        imageViewFotoDetalheNoticia.setColorFilter(Color.parseColor("#8190A5"), PorterDuff.Mode.MULTIPLY);
    }

    private void retornaDetalheListaNoticias(Article not,Noticias not2) {

        textViewTituloDetalheNoticia.setText(not.getTitle());

        textViewSubTituloDetalheNoticia.setText(not.getDescription());

        textViewHorarioDetalheNoticia.setText(not.getPublishedAt());

        textViewAssuntoDetalheNoticia.setText((CharSequence) not2.getArticle());

        textViewConteudoDetalheNoticia.setText(not.getDescription());

        getImage(not);

    }
    public void getImage(Article result){
        if (result.getUrlToImage() != null){
            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get()
                    .load(result.getUrlToImage())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageViewFotoDetalheNoticia);
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
                        DetalheNoticiaActivity.this, MainActivy.class);

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
                        DetalheNoticiaActivity.this, MainActivy.class);

                //Chama o fragmento da tela de LoginFragment(atraves de um flag 'Tela' com valor 'LoginFragment')
                //para cadastrar o ler noticia depois
                intentLerDepois.putExtra("TELA", "LOGIN");

                startActivity(intentLerDepois);
            }
        });
    }
}