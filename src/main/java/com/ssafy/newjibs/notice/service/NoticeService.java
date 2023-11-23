package com.ssafy.newjibs.notice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.dto.NoticePostDto;
import com.ssafy.newjibs.notice.repository.NoticeRepository;
import com.ssafy.newjibs.notice.util.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;
	private final NoticeMapper noticeMapper;

	public void createNotice(NoticePostDto noticePostDto) {
		noticeRepository.save(noticeMapper.toEntity(noticePostDto));
	}

	public NoticeDto readNotice(Long noticeId) {
		Notice notice = noticeRepository.findByNoticeId(noticeId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
		return noticeMapper.toDto(notice);
	}

	public List<NoticeListDto> readAllNotice() {
		return noticeRepository.findAllByIsDeletedFalse()
			.stream()
			.map(noticeMapper::toListDto)
			.collect(Collectors.toList());
	}

	public void updateNotice(Long noticeId, NoticePostDto noticePostDto) {
		Notice notice = noticeRepository.findByNoticeId(noticeId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
		notice.setTitle(noticePostDto.getTitle());
		notice.setContent(noticePostDto.getContent());// dirty checking
	}

	public void deleteNotice(Long noticeId) {
		Notice notice = noticeRepository.findByNoticeId(noticeId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
		notice.setIsDeleted(true);// soft deletion
	}
}
