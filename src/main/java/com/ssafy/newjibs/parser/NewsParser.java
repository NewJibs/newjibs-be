package com.ssafy.newjibs.parser;

import com.ssafy.newjibs.news.dto.NewsContentDto;
import com.ssafy.newjibs.news.dto.NewsTitleDto;
import com.ssafy.newjibs.parser.util.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsParser {
    private final JsonMapper jsonMapper;

    public List<NewsTitleDto> parseNewsTitles(String script) {
        String JSON_START = "pageInfo = ";
        String JSON_END = "}\n";

        // JSON data extraction
        int start = script.indexOf(JSON_START) + JSON_START.length();
        int end = script.indexOf(JSON_END, start) + 1;
        String jsonText = script.substring(start, end).replace("=", ":");// js to json

        JSONObject jsonObject = new JSONObject(jsonText);
        List<NewsTitleDto> list = new ArrayList<>();
        // JSON parse
        try {
            // field extraction
            JSONArray newsList = jsonObject.getJSONArray("newsList");
            for (int i = 0; i < newsList.length(); i++) {
                list.add(jsonMapper.toNewsMainDto(newsList.getJSONObject(i)));
            }
        } catch (Exception e) {// exception
            e.printStackTrace();
        }
        return list;
    }

    public NewsContentDto parseNewsContent(String title, String content, NewsTitleDto newsTitleDto) {
        String writer = "";
        content = content.replaceAll("<br>\\s*<br>", "<br>");// 개행 한 줄로 축소

        StringBuilder parseStr = new StringBuilder();
        String[] lines = content.split("<br>");
        for (String line : lines) {// todo: kmp
            if (line.contains("end_photo_org")) {// 줄에 img 관련 클래스가 있는 경우 스킵
                continue;
            }

            if (line.contains("기자") && line.contains("@")) {
                writer = line;
                break;
            }
            parseStr.append(line);
        }

        // 나머지 HTML 태그를 제거
        content = parseStr.toString().replaceAll("<[^>]+>", "");
        return NewsContentDto.builder()
                .articleId(newsTitleDto.getArticleId())
                .datetime(newsTitleDto.getDatetime())
                .dayOfTheWeek(newsTitleDto.getDayOfTheWeek())
                .officeHname(newsTitleDto.getOfficeHname())
                .contentTitle(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
