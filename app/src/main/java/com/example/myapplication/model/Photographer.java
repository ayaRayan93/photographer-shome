package com.example.myapplication.model;

import java.io.Serializable;

public class Photographer implements Serializable {

    String Photographer_ID,PhotographerName,Location,Facebook,Behance,WhatsApp,Instagram,ProfileImage;

    public String getPhotographer_ID() {
        return Photographer_ID;
    }

    public void setPhotographer_ID(String photographer_ID) {
        Photographer_ID = photographer_ID;
    }

    public String getPhotographerName() {
        return PhotographerName;
    }

    public void setPhotographerName(String photographerName) {
        PhotographerName = photographerName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getBehance() {
        return Behance;
    }

    public void setBehance(String behance) {
        Behance = behance;
    }

    public String getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        WhatsApp = whatsApp;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }
}
