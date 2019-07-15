package br.com.dhnews.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import br.com.dhnews.model.Article;

public class Converters {

    //Conversão para Datas
    @TypeConverter
    public Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }
    @TypeConverter
    public Long toTimestamp(Date date) {
        return date.getTime();
    }

    //Conversão para lista de Strings
    @TypeConverter
    public List<String> fromString(String value) {
        Type listType = (Type) new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public String fromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
    @TypeConverter
    public List<Article> fromArticleList(String value) {
        Type listType = (Type) new TypeToken<List<Article>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
}
