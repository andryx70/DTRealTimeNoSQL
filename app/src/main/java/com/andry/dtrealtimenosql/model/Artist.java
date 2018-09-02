package com.andry.dtrealtimenosql.model;


public class Artist {
    private String id;
    private String name;

    public Artist() {
    }

    private String genere;


    public Artist(String id, String name, String genere) {
        this.id = id;
        this.name = name;
        this.genere = genere;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
