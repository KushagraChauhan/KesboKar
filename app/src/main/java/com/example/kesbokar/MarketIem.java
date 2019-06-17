package com.example.kesbokar;

public class MarketIem {
    private String img,busi_name,busi_synop,url,city,title;
    int id;

    public MarketIem(String img,String busi_name,String busi_synop,String url,String city,int id, String title)
    {
        this.img=img;
        this.title=title;
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
}
