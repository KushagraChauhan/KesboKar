package com.kesbokar.kesbokar;

public class GetHelpDesk {
    private String date,subject,reply;
    int id1;

    public GetHelpDesk(String date, String subject, String reply,int id1) {
        this.date=date;
        this.subject=subject;
        this.reply=reply;
        this.id1=id1;
    }

    public String getDate() {
        return date;
    }

    public String getReply() {
        return reply;
    }

    public String getSubject() {
        return subject;
    }

    public int getId1() {
        return id1;
    }
}
