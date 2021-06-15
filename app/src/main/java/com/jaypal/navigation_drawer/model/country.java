package com.jaypal.navigation_drawer.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class country extends Intent implements Parcelable {
    String name;
    int rank;
    String about;
    String currency;
    String timezone;
    String language;
    String capital;
    String whether;
    String link;
    String inshort;

    public String getInshort() {
        return inshort;
    }

    public void setInshort(String inshort) {
        this.inshort = inshort;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public country(String name, int rank, String about, String currency, String timezone, String language, String capital, String whether, String link,String inshort) {
        this.name = name;
        this.rank = rank;
        this.about = about;
        this.currency = currency;
        this.timezone = timezone;
        this.language = language;
        this.capital = capital;
        this.whether = whether;
        this.link = link;
        this.inshort=inshort;
    }



    public country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getWhether() {
        return whether;
    }

    public void setWhether(String whether) {
        this.whether = whether;
    }


    @Override
    public int describeContents() {
        return 0;
    }
     protected country(Parcel in)
     {
         name = in.readString();
         rank = in.readInt();
         this.about = in.readString();
         this.currency = in.readString();
         this.timezone = in.readString();
         this.language = in.readString();
         this.capital = in.readString();
         this.whether = in.readString();
         this.link = in.readString();
         this.inshort= in.readString();
     }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(whether);
        parcel.writeString(capital);
        parcel.writeString(language);
        parcel.writeString(timezone);
        parcel.writeString(currency);
        parcel.writeString(about);
        parcel.writeString(inshort);
        parcel.writeString(link);
        parcel.writeInt(rank);
    }
    public static final Creator<country>CREATOR=new Creator<country>() {
        @Override
        public country createFromParcel(Parcel parcel) {
            return new country(parcel);
        }

        @Override
        public country[] newArray(int i) {
            return new country[i];
        }
    };
}
