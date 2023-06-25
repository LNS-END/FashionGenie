package com.example.fashion;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Shoes {
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int ID =0;
    @Expose
    private String Brand;
    @Expose
    private String Category;
    @Expose
    private String Color;
    @Expose
    private String Season;
    @Expose
    private String Textile;
    @Expose
    private String Cloth;

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

    public String getCloth() {
        return Cloth;
    }

    public void setCloth(String cloth) {
        Cloth = cloth;
    }

    public void loadImageIntoImageView(ImageView imageView) {
        if (ImagePath != null) {
            File imgFile = new File(ImagePath);
            if (imgFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
