package com.example.fashion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shoes {
    @PrimaryKey(autoGenerate = true)
    private int ID =0;
    private String Brand;
    private String Category;
    private String Color;
    private String Season;
    private String Textile;
    private String Style;

    private String ImagePath;

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public String getTextile() {
        return Textile;
    }

    public void setTextile(String textile) {
        Textile = textile;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }


}
