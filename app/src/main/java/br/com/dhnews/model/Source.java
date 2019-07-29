
package br.com.dhnews.model;

import com.google.gson.annotations.Expose;


public class Source {

    @Expose
    private Object id;
    @Expose
    private String name;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
