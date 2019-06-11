package br.com.dhnews.model;

public class Noticias {

    private String tituloNoticia;
    private String descricaoNoticia;

    public Noticias() {
    }

    public Noticias(String tituloNoticia, String descricaoNoticia) {
        this.tituloNoticia = tituloNoticia;
        this.descricaoNoticia = descricaoNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getDescricaoNoticia() {
        return descricaoNoticia;
    }

    public void setDescricaoNoticia(String descricaoNoticia) {
        this.descricaoNoticia = descricaoNoticia;
    }




}
