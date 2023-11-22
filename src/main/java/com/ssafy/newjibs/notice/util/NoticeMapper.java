package com.ssafy.newjibs.notice.util;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;

@Component
public class NoticeMapper {
	public Notice toEntity(NoticeDto noticeDto, String url) {
		return Notice.builder()
			.title(noticeDto.getTitle())
			.author(noticeDto.getAuthor())
			.content(noticeDto.getContent())
			.imageUrl(url)
			.date(noticeDto.getDate())
			.isDeleted(false)
			.build();
	}

	public NoticeDto toDto(Notice notice) {
		return NoticeDto.builder()
			.title(notice.getTitle())
			.author(notice.getAuthor())
			.content(notice.getContent())
			.imageUrl(notice.getImageUrl())
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
