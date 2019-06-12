package br.com.dhnews.model;

public class Noticias {

    private String tituloNoticia;
    private String descricaoNoticia;
    private String horaAssuntoNoticia;
    private int imagemNoticias;

    public Noticias() {
    }

    public Noticias(String tituloNoticia, String descricaoNoticia, String horaAssuntoNoticia, int imagemNoticias) {
        this.tituloNoticia = tituloNoticia;
        this.descricaoNoticia = descricaoNoticia;
        this.horaAssuntoNoticia = horaAssuntoNoticia;
        this.imagemNoticias = imagemNoticias;
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

    public String getHoraAssuntoNoticia() {
        return horaAssuntoNoticia;
    }

    public void setHoraAssuntoNoticia(String horaAssuntoNoticia) {
        this.horaAssuntoNoticia = horaAssuntoNoticia;
    }

    public int getImagemNoticias() {
        return imagemNoticias;
    }

    public void setImagemNoticias(int imagemNoticias) {
        this.imagemNoticias = imagemNoticias;
    }
}