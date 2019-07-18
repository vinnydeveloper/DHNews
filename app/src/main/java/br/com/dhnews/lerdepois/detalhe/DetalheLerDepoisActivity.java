package br.com.dhnews.lerdepois.detalhe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.dhnews.R;
import br.com.dhnews.model.Article;
import br.com.dhnews.model.Noticias;
import br.com.dhnews.view.MainActivy;

public class DetalheLerDepoisActivity extends AppCompatActivity {

    //Declaracao de atributos
    private TextView textViewTituloDetalheLerDepois;
    private TextView textViewSubTituloDetalheLerDepois;
    private TextView textViewHorarioDetalheLerDepois;
    private TextView textViewAssuntoDetalheLerDepois;
    private TextView textViewConteudoDetalheLerDepois;
    private ImageView imageViewBackDetalheLerDepois;
    private ImageView imageViewShareDetalheLerDepois;
    private ImageView imageViewBookMarkDetalheLerDepois;
    private ImageView imageViewFotoDetalheLerDepois;
    private Article noticia;
    private Noticias noticias1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_ler_depois);

        //Metodo para inicializar as Views
        initViews();

        //Valido se veio algum dado na intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            Noticias noticias = getIntent().getParcelableExtra("NOTICIAS");

            if (noticias != null) {

                //Retorna o detalhe da noticia que foi selecionada para Ler depois
                retornaDetalheListaLerDepois(noticia,noticias1);

                //Metodo para voltar para a tela com a Lista de noticias que será lida depois
                chamaListaNoticiaLerDepois();

                //Metodo para acessar os aplicativos de compartilhamento de dados das noticias
                //que podem ser lidas depois
                compartilharNoticiaLerDepois();

                //Metodo para desmarcar a opção ler depois da noticia
                desmarcaLerDepois();
            }
        }
    }

    private void initViews() {
        textViewTituloDetalheLerDepois = findViewById(R.id.textViewTituloDetalheLerDepois);
        textViewSubTituloDetalheLerDepois = findViewById(R.id.textViewSubTituloDetalheLerDepois);
        textViewHorarioDetalheLerDepois = findViewById(R.id.textViewHorarioDetalheLerDepois);
        textViewAssuntoDetalheLerDepois = findViewById(R.id.textViewAssuntoDetalheLerDepois);
        textViewConteudoDetalheLerDepois = findViewById(R.id.textViewConteudoDetalheLerDepois);
        imageViewBackDetalheLerDepois = findViewById(R.id.imagemBackDetalheLerDepois);
        imageViewShareDetalheLerDepois = findViewById(R.id.imagemShareDetalheLerDepois);
        imageViewBookMarkDetalheLerDepois = findViewById(R.id.imagemBookMarkDetalheLerDepois);
        imageViewFotoDetalheLerDepois = findViewById(R.id.imagemDetalheLerDepois);
    }

    private void retornaDetalheListaLerDepois(Article noticias,Noticias noticias1) {

        textViewTituloDetalheLerDepois.setText(noticias.getTitle());

        textViewSubTituloDetalheLerDepois.setText(noticias.getDescription());

        textViewHorarioDetalheLerDepois.setText(noticias.getPublishedAt());

        textViewAssuntoDetalheLerDepois.setText((CharSequence) noticias1.getArticle());

        textViewConteudoDetalheLerDepois.setText(noticias.getDescription());

        getImage(noticias);

    }
    public void getImage(Article result){
        if (result.getUrlToImage() != null){
            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get()
                    .load(result.getUrlToImage())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageViewFotoDetalheLerDepois);
        }
    }


    private void chamaListaNoticiaLerDepois() {
        imageViewBackDetalheLerDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela com a Lista de Noticias ler depois

                //Chama a Main Activity que verifica qual opcao do Menu Principal foi acionado para
                //chamar a tela/fragmento correspondente
                Intent intentListaLerDepois = new Intent(
                        DetalheLerDepoisActivity.this, MainActivy.class);

                //Chama o fragmento da tela de Noticias Ler depois(atraves de um flag 'Tela'
                // com valor 'LERDEPOIS')  para retornar para a lista de noticias ler depois
                intentListaLerDepois.putExtra("TELA", "LERDEPOIS");

                startActivity(intentListaLerDepois);
            }
        });
    }

    private void compartilharNoticiaLerDepois() {
        imageViewShareDetalheLerDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Acao de envio na intencao de chamar outra Actitivity
                Intent intentCompartilhar = new Intent(Intent.ACTION_SEND);

                //Envia texto no compartilhamento
                intentCompartilhar.putExtra(
                        Intent.EXTRA_TEXT, "Compartilhando noticias ler depois");

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

    private void desmarcaLerDepois() {
        imageViewBookMarkDetalheLerDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //caixa de dialogo
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Remover Notícia?");
                alertDialog.setMessage("");
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("MANTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.setPositiveButton("REMOVER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        /*
                        //Chama a tela de Detalhe com o bookmark desmarcado
                        Intent intentLerDepois = new Intent(
                         DetalheLerDepoisActivity.this, DetalheNoticiaActivity.class);

                        //Envia flag para retornar para a lista de noticias sem ler depois
                        intentLerDepois.putExtra("NOTICIAS", noticias);

                        startActivity(intentLerDepois);
                        */

                        //**************************************************************************
                        //Ajuste temporário até a inclusão do banco de dados para validação de dados
                        //**************************************************************************
                        //Chama a tela com a Lista de Noticias
                        //Chama a Main Activity que verifica qual opcao do Menu Principal foi
                        // acionado para chamar a tela/fragmento correspondente
                        Intent intentListaNoticias = new Intent(
                                DetalheLerDepoisActivity.this, MainActivy.class);

                        //Chama o fragmento da tela de Noticias(atraves de um flag 'Tela' com valor
                        //'Noticia')  para retornar para a lista de noticias
                        intentListaNoticias.putExtra("TELA", "NOTICIA");

                        startActivity(intentListaNoticias);
                        //**************************************************************************
                    }
                });

                AlertDialog alertDialog1 = alertDialog.create();
                alertDialog1.show();
            }
        });
    }
}
