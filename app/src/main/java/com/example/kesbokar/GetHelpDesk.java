package com.example.kesbokar;

public class GetHelpDesk {
    private String date,subject,reply;

    public GetHelpDesk(String date, String subject, String reply) {
        this.date=date;
        this.subject=subject;
        this.reply=reply;
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
}
