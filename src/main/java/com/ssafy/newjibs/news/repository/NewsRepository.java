package com.ssafy.newjibs.news.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.newjibs.news.domain.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	Optional<News> findByArticleId(String articleId);

	List<News> findAllByIsDeletedFalse();

	Boolean existsByArticleId(String articleId);
}
