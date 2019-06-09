package br.com.dhnews.detalhenoticia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.dhnews.MainActivy;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        //Metodo para inicializar as Views
        initViews();

        //****************************DADOS TEMPORARIOS*********************************************
        //Estes valores fixos sao temporarios, pois os dados serao alimentados pela tela anterior
        //que Lista as noticias

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

        //****************************DADOS TEMPORARIOS*********************************************

        //Metodo para voltar para a tela com a Lista de noticias
        chamaListaNoticia();

        //Metodo para acessar os aplicativos de compartilhamento de dados
        compartilharNoticia();

        //Metodo para chamar a tela de Login para cadastrar da opcao ler noticia depois
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
                        DetalheNoticiaActivity.this, MainActivy.class);

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

                //Chama o fragmento da tela de Login(atraves de um flag 'Tela' com valor 'Login')
                //para cadastrar o ler noticia depois
                intentLerDepois.putExtra("TELA", "LOGIN");

                startActivity(intentLerDepois);
            }
        });
    }
}