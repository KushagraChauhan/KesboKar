package com.kesbokar.kesbokar;

public class CarDetailsSeries {
    private int id;
    private String name, des_body, des_engine;
    public CarDetailsSeries(int id,String name,String des_body,String des_engine)
    {
        this.id=id;
        this.name=name;
        this.des_body=des_body;
        this.des_engine=des_engine;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDes_body() {
        return des_body;
    }
    public String getDes_engine()
    {
        return des_engine;
    }

}
