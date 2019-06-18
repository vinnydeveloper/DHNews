package br.com.dhnews.detalhenoticia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dhnews.lerdepois.model.Noticia;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.view.MainActivy;
import br.com.dhnews.R;

public class DetalheNoticiaActivity extends AppCompatActivity {

    //Declaracao de atributos
    private TextView textViewTituloDetalheNoticia;
    private TextView textViewSubTituloDetalheNoticia;
    private TextView textViewHorarioDetalheNoticia;
    private TextView textViewConteudoDetalheNoticia;
    private ImageView imageViewBackDetalheNoticia;
    private ImageView imageViewShareDetalheNoticia;
    private ImageView imageViewBookMarkDetalheNoticia;
    private ImageView imageViewFotoDetalheNoticia;
    private Noticias noticias;

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

    private void initViews() {
        textViewTituloDetalheNoticia = findViewById(R.id.textViewTituloNoticiaDetalhe);
        textViewSubTituloDetalheNoticia = findViewById(R.id.textViewSubTituloNoticiaDetalhe);
        textViewHorarioDetalheNoticia = findViewById(R.id.textViewHorarioNoticiaDetalhe);
        textViewConteudoDetalheNoticia = findViewById(R.id.textViewConteudoNoticiaDetalhe);
        imageViewBackDetalheNoticia = findViewById(R.id.imagemBackNoticiaDetalhe);
        imageViewShareDetalheNoticia = findViewById(R.id.imagemShareNoticiaDetalhe);
        imageViewBookMarkDetalheNoticia = findViewById(R.id.imagemBookMarkNoticiaDetalhe);
        imageViewFotoDetalheNoticia = findViewById(R.id.imagemNoticiaDetalhe);
    }

    private void retornaDetalheListaNoticias(Noticias noticias) {

        textViewTituloDetalheNoticia.setText(noticias.getTituloNoticia());

        textViewSubTituloDetalheNoticia.setText(noticias.getDescricaoNoticia());

        textViewHorarioDetalheNoticia.setText(noticias.getHoraAssuntoNoticia());

        textViewConteudoDetalheNoticia.setText(noticias.getDescricaoNoticia());

        imageViewFotoDetalheNoticia.setImageResource(noticias.getImagemNoticias());

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