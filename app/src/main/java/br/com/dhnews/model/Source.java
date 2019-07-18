package br.com.dhnews.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "noticias")
public class Source {

    @Expose
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @Expose
    @SerializedName("name")
    private String name;

    @Ignore
    public Source() {
    }

    public Source(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
