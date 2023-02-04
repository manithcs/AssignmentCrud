package com.hello.repository;

import com.hello.model.Article;

import java.util.ArrayList;

public interface ArticleRepository {
    boolean insertArticle(Article article);
    boolean updateArticle(Article article);
    boolean deleteArticle(long id);
    ArrayList<Article> findAllArticle();
    Article findArticlById(long id);

}
