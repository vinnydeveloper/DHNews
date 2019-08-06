
package br.com.dhnews.model.noticias;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Noticias {

    @Expose
    private List<Article> articles;
    @Expose
    private String status;
    @Expose
    private Long totalResults;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

}
