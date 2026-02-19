package com.tp.store;

import com.tp.store.bo.Article;
import com.tp.store.dto.ApiResponse;
import com.tp.store.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreApplicationTests {

	@Autowired
	private ArticleService articleService;

	private final Locale locale = Locale.FRANCE;

	@Test
	void testGetAll() {

		ApiResponse<?> response = articleService.getAll(locale);

		assertEquals(202, response.getCode());
		assertNotNull(response.getData());
	}

	@Test
	void testSaveCreation() {

		Article article = new Article();
		article.setTitle("Test creation");

		ApiResponse<Article> response = articleService.save(article, locale);

		assertEquals(202, response.getCode());
		assertNotNull(response.getData());
	}

	@Test
	void testGetByIdFound() {

		Article article = new Article();
		article.setTitle("Test get by id");

		ApiResponse<Article> saved = articleService.save(article, locale);

		ApiResponse<Article> response =
				articleService.getById(saved.getData().getId(), locale);

		assertEquals(202, response.getCode());
		assertNotNull(response.getData());
	}

	@Test
	void testGetByIdNotFound() {

		ApiResponse<Article> response =
				articleService.getById(99999L, locale);

		assertEquals(703, response.getCode());
		assertNull(response.getData());
	}

	@Test
	void testDeleteFound() {

		Article article = new Article();
		article.setTitle("Test delete");

		ApiResponse<Article> saved = articleService.save(article, locale);

		ApiResponse<Void> response =
				articleService.delete(saved.getData().getId(), locale);

		assertEquals(202, response.getCode());
	}

	@Test
	void testDeleteNotFound() {

		ApiResponse<Void> response =
				articleService.delete(99999L, locale);

		assertEquals(703, response.getCode());
	}
}
