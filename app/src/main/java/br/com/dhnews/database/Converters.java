package br.com.dhnews.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import br.com.dhnews.model.noticias.Source;

public class Converters {
    @TypeConverter
    public Source fromSource(String value) {
        Type listType = (Type) new TypeToken<Source>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromSource(Source list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public Object fromObject(String value) {
        Type listType = (Type) new TypeToken<Object>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromObject(Object list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
