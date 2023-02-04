package com.hello.controller;

import com.hello.model.Article;
import com.hello.repository.implement.ArticleRepositoryImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class ArticleController {
    private ArticleRepositoryImp articleRepositoryImp;

    @RequestMapping({"/","Article-list"})
    public String ArticleListPage(ModelMap modelMap) throws SQLException, ClassNotFoundException {
        articleRepositoryImp = new ArticleRepositoryImp();
        modelMap.addAttribute("Article",articleRepositoryImp.findAllArticle());
        modelMap.addAttribute("message","Article List");
        return "article-list";

    }
    @RequestMapping({"/article/{id}"})
        public String articleViewByIdpage(@PathVariable("id") long id, ModelMap modelMap) throws SQLException, ClassNotFoundException {
        articleRepositoryImp = new ArticleRepositoryImp();
        modelMap.addAttribute("article",articleRepositoryImp.findArticlById(id));
        modelMap.addAttribute("message","article View By ID");
        return  "article-view";

    }
    @RequestMapping("/article/{id}/delete")
    public String articleDeleteById(@PathVariable long id){
        articleRepositoryImp.deleteArticle(id);
        return  "redirect:/";
    }



    @RequestMapping("/article-insert")
    public String ArticleInsertPage(ModelMap modelMap){
        modelMap.addAttribute("message","add New Article");
        modelMap.addAttribute("article",new Article());
        return  "article-insert";
    }

    @RequestMapping(value = "/article-insert",method = RequestMethod.POST)
    public String insertArticle(@ModelAttribute Article article){
        if(articleRepositoryImp.insertArticle(article)){
            System.out.println("article insert sucessfully");

        }else {
            System.out.println("article did not insert");
        }
        return "redirect:/";
    }

    @RequestMapping("/article-update/{id}")
    public  String articleUpdatepage(ModelMap modelMap, @PathVariable long id){
        modelMap.addAttribute("message","update article info");
        modelMap.addAttribute("article",articleRepositoryImp.findArticlById(id));
        return "article-update";
    }

    @RequestMapping(value = "ariticle-update",method = RequestMethod.PUT)
        public String updateArticle(@ModelAttribute Article article){

        if(articleRepositoryImp.updateArticle(article)){
            System.out.println("article updated Sucessfully ");
        }else {
            System.out.println("article did not update");
        }
        return "redirect:/";

        }


}

