package com.kesbokar.kesbokar;

public class Get_for_reply {
    String ReplyMessage,ReplyBy,Date;
    int user_id,id1;
    public Get_for_reply(String ReplyMessage,String ReplyBy,String Date,int user_id,int id1){
        this.ReplyMessage=ReplyMessage;
        this.ReplyBy=ReplyBy;
        this.Date=Date;
        this.user_id=user_id;
        this.id1=id1;
    }

    public int getId1() {
        return id1;
    }

    public String getDate() {
        return Date;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getReplyBy() {
        return ReplyBy;
    }

    public String getReplyMessage() {
        return ReplyMessage;
    }
}
