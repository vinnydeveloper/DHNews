
package br.com.dhnews.model.noticias;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "article")
public class Article implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long key;

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

    public Article() {
    }

    protected Article(Parcel in) {
        content = in.readString();
        description = in.readString();
        publishedAt = in.readString();
        title = in.readString();
        url = in.readString();
        urlToImage = in.readString();
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

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(description);
        dest.writeString(publishedAt);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(urlToImage);
    }
}
