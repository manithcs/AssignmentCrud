package com.hello.repository.implement;

import com.hello.configuration.WebConfiguration;
import com.hello.model.Article;
import com.hello.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Repository

public class ArticleRepositoryImp implements ArticleRepository {
        private Connection connection;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;
        private String sql;


    public ArticleRepositoryImp() throws ClassNotFoundException {
        this.connection = WebConfiguration.initDB();
    }



    @Override
    public boolean insertArticle(Article article) {
        sql = "INSERT INTO article(title,content,createdate)VALUES(?,?,?) ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getCreatedate());

        if(preparedStatement.executeUpdate()!=0){
            return  true;
        }


        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateArticle(Article article) {
            sql ="UPDATE  article SET  title=?,content=?,createdate=? WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getCreatedate());
            preparedStatement.setLong(4,article.getId());

            if(preparedStatement.executeUpdate()!=0){
                return  true;
            }


        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteArticle(long id) {
            sql ="DELETE FROM article WHERE id=?";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                if(preparedStatement.executeUpdate()!=0){
                    return  true;
                }

            }catch (SQLException ex){

            }


        return false;
    }

    @Override
    public ArrayList<Article> findAllArticle() {
            sql ="SELECT * FROM article;";
            ArrayList<Article>articles = new ArrayList();
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    articles.add(new Article(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getString("createdate")
                    )
                    );
                }

            }catch (SQLException ex){
                ex.printStackTrace();
            }

        return null;
    }

    @Override
    public Article findArticlById(long id) {
            sql ="SELECT * FROM article WHERE id=? ";
            Article article = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    article = new Article(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getString("createdate")
                    );
                }

                return article;
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        return null;
    }
}
