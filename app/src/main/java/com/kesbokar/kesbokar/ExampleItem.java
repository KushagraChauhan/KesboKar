package com.kesbokar.kesbokar;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleItem implements Parcelable {
    private String img,busi_name,busi_synop,url,city,heading;
    int id;
    double ratings;

    protected ExampleItem(Parcel in) {
        img = in.readString();
        busi_name = in.readString();
        busi_synop = in.readString();
        url = in.readString();
        city = in.readString();
        id = in.readInt();
        ratings = in.readDouble();
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };

    public void setImg(String img) {
        this.img = img;
    }

    public void setBusi_name(String busi_name) {
        this.busi_name = busi_name;
    }

    public void setBusi_synop(String busi_synop) {
        this.busi_synop = busi_synop;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
    public ExampleItem(){

    }


    public ExampleItem(String img,String busi_name,String busi_synop,String url,String city,int id, double ratings,String heading)
    {
        this.img=img;
        this.ratings=ratings;
        this.busi_name=busi_name;
        this.busi_synop=busi_synop;
        this.city=city;
        this.url=url;
        this.id=id;
        this.heading=heading;
    }
    public String getCity()
    {
        return city;
    }
    public String getUrl()
    {
        return url;
    }
    public String getImg()
    {
        return img;
    }
    public int getId()
    {
        return id;
    }
    public double getratings()
    {
        return ratings;
    }
    public String getBusi_name()
    {
        return busi_name;
    }
    public String getBusi_synop()
    {
        return busi_synop;
    }

    public String getHeading() {
        return heading;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(img);
        parcel.writeString(busi_name);
        parcel.writeString(busi_synop);
        parcel.writeString(url);
        parcel.writeString(city);
        parcel.writeInt(id);
        parcel.writeDouble(ratings);
    }
}
