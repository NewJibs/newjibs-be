package com.ssafy.newjibs.notice.service;

import static com.ssafy.newjibs.utility.constant.ImageUploadPath.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.repository.NoticeRepository;
import com.ssafy.newjibs.notice.util.NoticeMapper;
import com.ssafy.newjibs.utility.service.S3Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
	private final S3Service s3Service;
	private final NoticeRepository noticeRepository;
	private final NoticeMapper noticeMapper;

	public void createNotice(NoticeDto noticeDto, MultipartFile image) throws IOException {
		String url = null;
		if (image != null) {
			url = s3Service.uploadImage(image, NOTICE_PATH.getPath());
		}
		noticeRepository.save(noticeMapper.toEntity(noticeDto, url));
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

	public void updateNotice(Long noticeId, NoticeDto noticeDto, MultipartFile image) throws IOException {
		Notice notice = noticeRepository.findByNoticeId(noticeId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
		if (image != null) {
			String beforeImageUrl = notice.getImageUrl();
			if (beforeImageUrl != null) {
				s3Service.deleteImageFromS3(beforeImageUrl);
			}
			notice.setImageUrl(s3Service.uploadImage(image, NOTICE_PATH.getPath()));
		}
		notice.setTitle(noticeDto.getTitle());
		notice.setContent(noticeDto.getContent());// dirty checking

	}

	public void deleteNotice(Long noticeId) {
		Notice notice = noticeRepository.findByNoticeId(noticeId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOTICE_NOT_FOUND));
		String image = notice.getImageUrl();
		if (image != null) {
			s3Service.deleteImageFromS3(image);
		}
		notice.setIsDeleted(true);// soft deletion
	}
}
