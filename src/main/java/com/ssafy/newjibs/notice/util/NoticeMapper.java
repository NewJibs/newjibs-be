package com.ssafy.newjibs.notice.util;

import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;

import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {
	public Notice toEntity(NoticeDto noticeDto) {
		return Notice.builder()
			.title(noticeDto.getTitle())
			.author(noticeDto.getAuthor())
			.content(noticeDto.getContent())
			.imgUrl(noticeDto.getImgUrl())
			.date(noticeDto.getDate())
			.isDeleted(false)
			.build();
	}

	public NoticeDto toDto(Notice notice) {
		return NoticeDto.builder()
			.title(notice.getTitle())
			.author(notice.getAuthor())
			.content(notice.getContent())
			.imgUrl(notice.getImgUrl())
			.date(notice.getDate())
			.build();
	}

	public NoticeListDto toListDto(Notice notice) {
		return NoticeListDto.builder()
			.title(notice.getTitle())
			.author(notice.getAuthor())
			.date(notice.getDate())
			.build();
	}
}
