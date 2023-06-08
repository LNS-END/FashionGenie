package com.example.fashion;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = com.example.fashion.HeadWear.class,
                parentColumns = "ID",
                childColumns = "headWearID"
                ),
        @ForeignKey(entity = Bag.class,
                parentColumns = "ID",
                childColumns = "bagID"),
        @ForeignKey(entity = com.example.fashion.Outer.class,
                parentColumns = "ID",
                childColumns = "outerID"),
        @ForeignKey(entity = Pants.class,
                parentColumns = "ID",
                childColumns = "pantsID"),
        @ForeignKey(entity = com.example.fashion.Shoes.class,
                parentColumns = "ID",
                childColumns = "shoesID"),
        @ForeignKey(entity = com.example.fashion.Top.class,
                parentColumns = "ID",
                childColumns = "topID")
})

public class Coordi {
    @PrimaryKey(autoGenerate = true)
    public int ID = 0;

    private String ImagePath;

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int headWearID;
    public int shoesID;
    public int pantsID;
    public int outerID;
    public int topID;
    public int bagID;

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
