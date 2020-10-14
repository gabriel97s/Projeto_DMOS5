package br.edu.ifsp.projeto_dmos5.model;

import java.io.Serializable;

public class Elephant implements Serializable {
    private String _id;
    private String affiliation;
    private String dob;
    private String dod;
    private String fictional;
    private String image;
    private Integer index;
    private String name;
    private String note;
    private String sex;
    private String species;
    private String wikilink;

    public Elephant(
            String _id
            , String affiliation
            , String dob
            , String dod
            , String fictional
            , String image
            , Integer index
            , String name
            , String note
            , String sex
            , String species
            , String wikilink
    ) {
        this._id = _id;
        this.affiliation = affiliation;
        this.dob = dob;
        this.dod = dod;
        this.fictional = fictional;
        this.image = image;
        this.index = index;
        this.name = name;
        this.note = note;
        this.sex = sex;
        this.species = species;
        this.wikilink = wikilink;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getFictional() {
        return fictional;
    }

    public void setFictional(String fictional) {
        this.fictional = fictional;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink) {
        this.wikilink = wikilink;
    }
}
