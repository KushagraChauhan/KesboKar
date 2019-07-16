package com.kesbokar.kesbokar;

public class inbox_reply_market {
    String ReplyMessage,ReplyBy,Date;
    int user_id,id1, enquiry_id;
    public inbox_reply_market(String ReplyMessage, String ReplyBy, String Date, int userId, int user_id){
        this.ReplyMessage=ReplyMessage;
        this.ReplyBy=ReplyBy;
        this.Date=Date;
        this.user_id=user_id;
        this.id1=id1;
        this.enquiry_id = enquiry_id;

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

    public int getEnquiry_id(){
        return enquiry_id;
    }

}

