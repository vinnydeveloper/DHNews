package br.com.dhnews.lerdepois.model;

public class Noticia {
    private String tituloText;
    private String conteudoText;
    private int imagemNoticia;
    private String assunto;
    private String tempoNoticia;

    public Noticia() {
    }

    public Noticia(String tituloText, String conteudoText, int imagemNoticia, String assunto, String tempoNoticia) {
        this.tituloText = tituloText;
        this.conteudoText = conteudoText;
        this.imagemNoticia = imagemNoticia;
        this.assunto = assunto;
        this.tempoNoticia = tempoNoticia;
    }

    public String getTituloText() {
        return tituloText;
    }

    public void setTituloText(String tituloText) {
        this.tituloText = tituloText;
    }

    public String getConteudoText() {
        return conteudoText;
    }

    public void setConteudoText(String conteudoText) {
        this.conteudoText = conteudoText;
    }

    public int getImagemNoticia() {
        return imagemNoticia;
    }

    public void setImagemNoticia(int imagemNoticia) {
        this.imagemNoticia = imagemNoticia;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTempoNoticia() {
        return tempoNoticia;
    }

    public void setTempoNoticia(String tempoNoticia) {
        this.tempoNoticia = tempoNoticia;
    }
}
