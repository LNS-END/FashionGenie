package com.example.fashion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LikedCoordi {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String ImagePath;

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    private int headWearID;
    private int topID;
    private int pantsID;
    private int shoesID;
    private int bagID;
    private int outerID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHeadWearID() {
        return headWearID;
    }

    public void setHeadWearID(int headWearID) {
        this.headWearID = headWearID;
    }

    public int getTopID() {
        return topID;
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public int getPantsID() {
        return pantsID;
    }

    public void setPantsID(int pantsID) {
        this.pantsID = pantsID;
    }

    public int getShoesID() {
        return shoesID;
    }

    public void setShoesID(int shoesID) {
        this.shoesID = shoesID;
    }

    public int getBagID() {
        return bagID;
    }

    public void setBagID(int bagID) {
        this.bagID = bagID;
    }

    public int getOuterID() {
        return outerID;
    }

    public void setOuterID(int outerID) {
        this.outerID = outerID;
    }


}
