package com.jaypal.navigation_drawer.model;

import java.util.ArrayList;

public class Doctor {
    String name;
    String hospital_name;
    String location;
    String Experience;
    int fees;
    ArrayList<String>lang;

    public ArrayList<String> getLang() {
        return lang;
    }

    public void setLang(ArrayList<String> lang) {
        this.lang = lang;
    }

    public ArrayList<String> getEdu() {
        return edu;
    }

    public void setEdu(ArrayList<String> edu) {
        this.edu = edu;
    }

    ArrayList<String>edu;
    String education;
    String speciality;
    float rating;
    String link;

    public Doctor() {
    }

    public Doctor(String name, String hospital_name, String location, String experience, int fees, ArrayList<String> lang, ArrayList<String> edu, String education, String speciality, float rating, String link) {
        this.name = name;
        this.hospital_name = hospital_name;
        this.location = location;
        Experience = experience;
        this.fees = fees;
        this.lang = lang;
        this.edu = edu;
        this.education = education;
        this.speciality = speciality;
        this.rating = rating;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
