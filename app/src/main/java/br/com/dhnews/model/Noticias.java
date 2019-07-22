package br.com.dhnews.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Entity(tableName = "noticias")
public class Noticias  {

    @SerializedName("status")
    @Expose
    private String status;

 //   @PrimaryKey(autoGenerate = true)
    @SerializedName("totalResult")
    @Expose
    private int totalResult;

    @SerializedName("articles")
    @Expose
    private List<Article> article;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    /*
package br.com.dhnews.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "noticias")

public class Noticias implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String tituloNoticia;
    private String descricaoNoticia;
    private String horaNoticia;
    private String assuntoNoticia;
    private int imagemNoticias;



    public Noticias() {
    }

    public Noticias(String tituloNoticia, String descricaoNoticia, String horaNoticia,
                    String assuntoNoticia, int imagemNoticias) {
        this.tituloNoticia = tituloNoticia;
        this.descricaoNoticia = descricaoNoticia;
        this.horaNoticia = horaNoticia;
        this.assuntoNoticia = assuntoNoticia;
        this.imagemNoticias = imagemNoticias;
    }

    protected Noticias(Parcel in) {
        id = in.readLong();
        tituloNoticia = in.readString();
        descricaoNoticia = in.readString();
        horaNoticia = in.readString();
        assuntoNoticia = in.readString();
        imagemNoticias = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(tituloNoticia);
        dest.writeString(descricaoNoticia);
        dest.writeString(horaNoticia);
        dest.writeString(assuntoNoticia);
        dest.writeInt(imagemNoticias);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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




}*/

}