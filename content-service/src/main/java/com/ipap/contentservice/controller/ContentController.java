package com.ipap.contentservice.controller;

import com.ipap.contentservice.model.Article;
import com.ipap.contentservice.service.ContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public List<Article> findAllArticles() {
        return contentService.findAllArticles();
    }

    @GetMapping("/header")
    public List<Article> findAllArticlesWithHeader() {
        return contentService.findAllArticlesWithHeader(Map.of("X-POWERED-BY", "Spring Framework 6"));
    }

    @GetMapping("/{id}")
    public Optional<Article> findArticleById(@PathVariable int id) {
        return contentService.findArticleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody Article article) {
        contentService.createArticle(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArticle(@PathVariable int id, @RequestBody Article article) {
        contentService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable int id) {
        contentService.deleteArticle(id);
    }
}
