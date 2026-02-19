package com.tp.store.service;

import com.tp.store.bo.Article;
import com.tp.store.dao.ArticleRepository;
import com.tp.store.dto.ApiResponse;
import com.tp.store.util.LocaleHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final LocaleHelper localeHelper;

    public ArticleService(ArticleRepository articleRepository, LocaleHelper localeHelper) {
        this.articleRepository = articleRepository;
        this.localeHelper = localeHelper;
    }

    public ApiResponse<List<Article>> getAll(Locale locale) {

        String message = localeHelper.getMessage("article.list.success", locale);

        return new ApiResponse<>(
                202,
                message,
                articleRepository.findAll()
        );
    }

    public ApiResponse<Article> getById(Long id, Locale locale) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isPresent()) {

            String message = localeHelper.getMessage("article.get.success", locale);

            return new ApiResponse<>(202, message, article.get());
        }

        String message = localeHelper.getMessage("article.get.notfound", locale);

        return new ApiResponse<>(703, message, null);
    }

    public ApiResponse<Void> delete(Long id, Locale locale) {

        if (articleRepository.existsById(id)) {

            articleRepository.deleteById(id);

            String message = localeHelper.getMessage("article.delete.success", locale);

            return new ApiResponse<>(202, message, null);
        }

        String message = localeHelper.getMessage("article.get.notfound", locale);

        return new ApiResponse<>(703, message, null);
    }

    public ApiResponse<Article> save(Article article, Locale locale) {

        if (article.getId() == null) {

            Article saved = articleRepository.save(article);

            String message = localeHelper.getMessage("article.save.success", locale);

            return new ApiResponse<>(202, message, saved);
        }

        Article updated = articleRepository.save(article);

        String message = localeHelper.getMessage("article.update.success", locale);

        return new ApiResponse<>(203, message, updated);
    }
}
