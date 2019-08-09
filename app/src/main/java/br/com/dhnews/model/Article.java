package br.com.dhnews.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private String tituloNoticia;
    private String descricaoNoticia;
    private String horaNoticia;
    private String assuntoNoticia;
    private int imagemNoticias;

    public Article() {
    }

    public Article(String tituloNoticia, String descricaoNoticia, String horaNoticia,
                   String assuntoNoticia, int imagemNoticias) {
        this.tituloNoticia = tituloNoticia;
        this.descricaoNoticia = descricaoNoticia;
        this.horaNoticia = horaNoticia;
        this.assuntoNoticia = assuntoNoticia;
        this.imagemNoticias = imagemNoticias;
    }

    protected Article(Parcel in) {
        tituloNoticia = in.readString();
        descricaoNoticia = in.readString();
        horaNoticia = in.readString();
        assuntoNoticia = in.readString();
        imagemNoticias = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    public String getHoraNoticia() {
        return horaNoticia;
    }

    public void setHoraNoticia(String horaNoticia) {
        this.horaNoticia = horaNoticia;
    }

    public String getAssuntoNoticia() {
        return assuntoNoticia;
    }

    public void setAssuntoNoticia(String assuntoNoticia) {
        this.assuntoNoticia = assuntoNoticia;
    }

    public int getImagemNoticias() {
        return imagemNoticias;
    }

    public void setImagemNoticias(int imagemNoticias) {
        this.imagemNoticias = imagemNoticias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tituloNoticia);
        dest.writeString(descricaoNoticia);
        dest.writeString(horaNoticia);
        dest.writeString(assuntoNoticia);
        dest.writeInt(imagemNoticias);
    }


}