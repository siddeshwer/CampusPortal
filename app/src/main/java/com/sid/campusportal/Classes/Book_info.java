package com.sid.campusportal.Classes;

public class Book_info
{
    private String subject,link_url;

    public Book_info(String subject, String link_url)
    {
        this.subject=subject;
        this.link_url=link_url ;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
}
