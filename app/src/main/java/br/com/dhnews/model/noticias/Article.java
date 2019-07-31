
package br.com.dhnews.model.noticias;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;


@Entity(tableName = "Article")
public class Article {

    @Expose
    private Object author;
    @Expose
    private String content;
    @Expose
    private String description;
    @Expose
    private String publishedAt;
    @Expose
    private Source source;
    @Expose
    private String title;
    @Expose
    private String url;
    @Expose
    private String urlToImage;

    protected Article(Parcel in) {
        content = in.readString();
        description = in.readString();
        publishedAt = in.readString();
        title = in.readString();
        url = in.readString();
        urlToImage = in.readString();
    }


    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


}
