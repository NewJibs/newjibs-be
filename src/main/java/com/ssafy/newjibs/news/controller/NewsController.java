package com.ssafy.newjibs.news.controller;

import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.news.service.NewsService;
import com.ssafy.newjibs.response.BaseResponse;
import com.ssafy.newjibs.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public BaseResponse<List<NewsTitleDto>> getAllNewsTitles() {
        List<NewsTitleDto> list = newsService.readAllNews();
        return new BaseResponse<>(ResponseStatus.SUCCESS, list);
    }

    @GetMapping("/{articleId}")
    public BaseResponse<NewsContentDto> getNewsContent(@PathVariable String articleId) {
        NewsContentDto newsContentDto = newsService.readNews(articleId);
        return new BaseResponse<>(ResponseStatus.SUCCESS, newsContentDto);
    }

    @DeleteMapping("/{articleId}")
    public BaseResponse<Void> removeNews(@PathVariable String articleId) {
        newsService.deleteNews(articleId);
        return new BaseResponse<>(ResponseStatus.SUCCESS);
    }
}
