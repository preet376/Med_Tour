package com.jaypal.navigation_drawer.model;

import java.util.ArrayList;
import java.util.List;

public class compare_tret {
    String name;
    List<String>cname;
    List<String> price;

    public compare_tret(String name, List<String> cname, List<String> price) {
        this.name = name;
        this.cname = cname;
        this.price = price;
    }

    public compare_tret() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCname() {
        return cname;
    }

    public void setCname(List<String> cname) {
        this.cname = cname;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }
}
