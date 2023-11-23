package com.ssafy.newjibs.notice.util;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.dto.NoticePostDto;

@Component
public class NoticeMapper {
	public Notice toEntity(NoticePostDto noticePostDto) {
		return Notice.builder()
			.title(noticePostDto.getTitle())
			.author("관리자")
			.content(noticePostDto.getContent())
			.date(noticePostDto.getDate())
			.isDeleted(false)
			.build();
	}

	public NoticeDto toDto(Notice notice) {
		return NoticeDto.builder()
			.title(notice.getTitle())
			.author(notice.getAuthor())
			.content(notice.getContent())
			.date(notice.getDate())
			.build();
	}

	public NoticeListDto toListDto(Notice notice) {
		return NoticeListDto.builder()
			.noticeId(notice.getNoticeId())
			.title(notice.getTitle())
			.author(notice.getAuthor())
			.date(notice.getDate())
			.build();
	}
}
