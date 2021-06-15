package com.jaypal.navigation_drawer.model;

import java.util.List;

public class hospital {
    String name;
    String location;
    String about;
    List<String>list;
    String country_name;
    String link;

    public hospital() {
    }

    public hospital(String name, String location,String country_name, String about, List<String> list, String link) {
        this.name = name;
        this.location = location;
        this.about = about;
        this.list = list;
        this.link = link;
        this.country_name=country_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
