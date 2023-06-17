package com.ipap.articlesservice.controller;

import com.ipap.articlesservice.model.Article;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final List<Article> articles;

    public ArticleController() {
        this.articles = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        Article art1 = new Article(1, "Politics", "This is the first topic about Politics");
        Article art2 = new Article(2, "Social", "This is the first topic about Social");
        Article art3 = new Article(3, "Financial", "This is the first topic about Financial");
        Article art4 = new Article(4, "Forecast", "This is the first topic about today's Forecast");
        this.articles.add(art1);
        this.articles.add(art2);
        this.articles.add(art3);
        this.articles.add(art4);
    }

    @GetMapping
    public List<Article> findAll() {
        return this.articles;
    }

    @GetMapping("/{id}")
    public Optional<Article> findById(@PathVariable int id) {
        return articles.stream().filter(article -> article.id().equals(id)).findFirst();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody Article article) {
        this.articles.add(article);
        return this.articles.get(this.articles.indexOf(article));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Article article) {
        articles.stream().filter(a -> a.id().equals(id))
                .findFirst()
                .ifPresent(a -> this.articles.set(this.articles.indexOf(a), article));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        articles.removeIf(a -> a.id().equals(id));
    }
}
