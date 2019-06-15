package com.example.kesbokar;

public class ExampleItem {
    private String img,busi_name,busi_synop,url,city;
    int id;
    double ratings;
    public ExampleItem(String img,String busi_name,String busi_synop,String url,String city,int id, double ratings)
    {
        this.img=img;
        this.ratings=ratings;
        this.busi_name=busi_name;
        this.busi_synop=busi_synop;
        this.city=city;
        this.url=url;
        this.id=id;
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
}
