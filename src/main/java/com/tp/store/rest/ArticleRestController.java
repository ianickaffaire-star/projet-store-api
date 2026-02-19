package com.tp.store.rest;

import com.tp.store.bo.Article;
import com.tp.store.dto.ApiResponse;
import com.tp.store.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ApiResponse<List<Article>> getAll(Locale locale) {
        return articleService.getAll(locale);
    }

    @GetMapping("/{id}")
    public ApiResponse<Article> getById(@PathVariable Long id, Locale locale) {
        return articleService.getById(id, locale);
    }

    @PostMapping
    public ApiResponse<Article> save(@RequestBody Article article, Locale locale) {
        return articleService.save(article, locale);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id, Locale locale) {
        return articleService.delete(id, locale);
    }
}
