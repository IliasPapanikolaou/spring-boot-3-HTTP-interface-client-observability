package com.ipap.contentservice.service;

import com.ipap.contentservice.client.ArticleClient;
import com.ipap.contentservice.model.Article;
import io.micrometer.observation.annotation.Observed;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContentService {

    private final ArticleClient articleClient;

    public ContentService(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @Observed(name = "get.findAllArticles")
    public List<Article> findAllArticles() {
        return articleClient.findAll();
    }

    @Observed(name = "get.findAllArticlesWithHeader")
    public List<Article> findAllArticlesWithHeader(Map<String, String> headers) {
        return articleClient.findAll(headers);
    }

    @Observed(name = "get.findArticleById")
    public Optional<Article> findArticleById(@PathVariable int id) {
        return articleClient.findOne(id);
    }

    @Observed(name = "post.createArticle")
    public void createArticle(@RequestBody Article article) {
        articleClient.create(article);
    }

    @Observed(name = "put.updateArticle")
    public void updateArticle(@PathVariable int id, @RequestBody Article article) {
        articleClient.update(article, id);
    }

    @Observed(name = "delete.deleteArticle")
    public void deleteArticle(@PathVariable int id) {
        articleClient.delete(id);
    }
}
