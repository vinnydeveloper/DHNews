package br.com.dhnews.view.lerdepois;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dhnews.R;
import br.com.dhnews.model.noticias.Article;
import br.com.dhnews.view.MainActivity;

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
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_ler_depois);

        //Metodo para inicializar as Views
        initViews();

        //Valido se veio algum dado na intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            Article article = getIntent().getParcelableExtra("NOTICIAS");

            if (article != null) {

                //Retorna o detalhe da noticia que foi selecionada para Ler depois
                retornaDetalheListaLerDepois(article);

                //Metodo para voltar para a tela com a Lista de article que será lida depois
                chamaListaNoticiaLerDepois();

                //Metodo para acessar os aplicativos de compartilhamento de dados das article
                //que podem ser lidas depois
                compartilharNoticiaLerDepois();

                //Metodo para desmarcar a opção ler depois da noticia
                imageViewBookMarkDetalheLerDepois.setOnClickListener(v-> desmarcaLerDepois());

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

    private void retornaDetalheListaLerDepois(Article article) {

        textViewTituloDetalheLerDepois.setText(article.getTitle());

        textViewSubTituloDetalheLerDepois.setText(article.getDescription());

        textViewHorarioDetalheLerDepois.setText(article.getPublishedAt());

        textViewConteudoDetalheLerDepois.setText(article.getContent());

        //imageViewFotoDetalheLerDepois.setImageResource(article.getUrlToImage());

    }


    private void chamaListaNoticiaLerDepois() {
        imageViewBackDetalheLerDepois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Chama a tela com a Lista de Article ler depois

                //Chama a Main Activity que verifica qual opcao do Menu Principal foi acionado para
                //chamar a tela/fragmento correspondente
                Intent intentListaLerDepois = new Intent(
                        DetalheLerDepoisActivity.this, MainActivity.class);

                //Chama o fragmento da tela de Article Ler depois(atraves de um flag 'Tela'
                // com valor 'LERDEPOIS')  para retornar para a lista de article ler depois
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
                        Intent.EXTRA_TEXT, "Compartilhando article ler depois");

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

                        //Envia flag para retornar para a lista de article sem ler depois
                        intentLerDepois.putExtra("NOTICIAS", article);

                        startActivity(intentLerDepois);
                        */

                        //**************************************************************************
                        //Ajuste temporário até a inclusão do banco de dados para validação de dados
                        //**************************************************************************
                        //Chama a tela com a Lista de Article
                        //Chama a Main Activity que verifica qual opcao do Menu Principal foi
                        // acionado para chamar a tela/fragmento correspondente

                        Intent intentListaNoticias = new Intent(
                                DetalheLerDepoisActivity.this, MainActivity.class);

                        //Chama o fragmento da tela de Article(atraves de um flag 'Tela' com valor
                        //'Noticia')  para retornar para a lista de article
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
