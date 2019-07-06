package com.kesbokar.kesbokar;

import android.os.Parcel;
import android.os.Parcelable;

public class MarketIem implements Parcelable {
    private String img,busi_name,busi_synop,url,city,title,price,heading;
    int id;

    public MarketIem(){}
    public MarketIem(String img,String busi_name,String busi_synop,String url,String city,int id, String title, String price,String heading)
    {
        this.img=img;
        this.title=title;
        this.busi_name=busi_name;
        this.busi_synop=busi_synop;
        this.city=city;
        this.url=url;
        this.id=id;
        this.price=price;
        this.heading=heading;
    }

    protected MarketIem(Parcel in) {
        img = in.readString();
        busi_name = in.readString();
        busi_synop = in.readString();
        url = in.readString();
        city = in.readString();
        title = in.readString();
        id = in.readInt();
    }

    public static final Creator<MarketIem> CREATOR = new Creator<MarketIem>() {
        @Override
        public MarketIem createFromParcel(Parcel in) {
            return new MarketIem(in);
        }

        @Override
        public MarketIem[] newArray(int size) {
            return new MarketIem[size];
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
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
    public String getprice()
    {
        return price;
    }
    public String getTitle()
    {
        return title;
    }
    public String getBusi_name()
    {
        return busi_name;
    }
    public String getBusi_synop()
    {
        return busi_synop;
    }
    public String getHeading()
    {
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
        parcel.writeString(title);
        parcel.writeInt(id);
    }
}
