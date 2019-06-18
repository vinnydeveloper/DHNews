package br.com.dhnews.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Noticias implements Parcelable {

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

    protected Noticias(Parcel in) {
        tituloNoticia = in.readString();
        descricaoNoticia = in.readString();
        horaAssuntoNoticia = in.readString();
        imagemNoticias = in.readInt();
    }

    public static final Creator<Noticias> CREATOR = new Creator<Noticias>() {
        @Override
        public Noticias createFromParcel(Parcel in) {
            return new Noticias(in);
        }

        @Override
        public Noticias[] newArray(int size) {
            return new Noticias[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tituloNoticia);
        dest.writeString(descricaoNoticia);
        dest.writeString(horaAssuntoNoticia);
        dest.writeInt(imagemNoticias);
    }
}