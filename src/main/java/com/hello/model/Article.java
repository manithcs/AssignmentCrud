package com.hello.model;

import java.util.Date;

public class Article {
    private long id;
    private String title;
    private String content;
    private String createdate;

    public Article() {
    }

    public Article(long id, String title, String content, String createdate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdate = createdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdate='" + createdate + '\'' +
                '}';
    }
}
