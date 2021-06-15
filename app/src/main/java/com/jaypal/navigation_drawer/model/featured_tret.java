package com.jaypal.navigation_drawer.model;

public class featured_tret {
    String link,tret1,tret2,title;

    public featured_tret(String link, String tret1, String tret2, String title) {
        this.link = link;
        this.tret1 = tret1;
        this.tret2 = tret2;
        this.title = title;
    }

    public featured_tret() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTret1() {
        return tret1;
    }

    public void setTret1(String tret1) {
        this.tret1 = tret1;
    }

    public String getTret2() {
        return tret2;
    }

    public void setTret2(String tret2) {
        this.tret2 = tret2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
