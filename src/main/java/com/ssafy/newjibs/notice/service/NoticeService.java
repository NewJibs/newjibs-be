package com.ssafy.newjibs.notice.service;

import com.ssafy.newjibs.notice.domain.Notice;
import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.repository.NoticeRepository;
import com.ssafy.newjibs.notice.util.NoticeMapper;
import com.ssafy.newjibs.notice.util.NoticeUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;
    private final NoticeUtility noticeUtility;

    public void createNotice(NoticeDto noticeDto) {
        Notice notice = noticeMapper.toEntity(noticeDto);
        if (!noticeUtility.isAdministrator(noticeDto.getAuthor())) {// todo: verify with login member's auth
            throw new RuntimeException();// exception (403 forbidden)
        }
        noticeRepository.save(notice);
    }

    public NoticeDto readNotice(Long noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(RuntimeException::new);// exception
        return noticeMapper.toDto(notice);
    }

    public List<NoticeListDto> readAllNotice() {
        return noticeRepository.findAllByIsDeletedFalse()
                .stream()
                .map(noticeMapper::toListDto)
                .collect(Collectors.toList());
    }

    public void updateNotice(Long noticeId, NoticeDto noticeDto) {
        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(RuntimeException::new);// exception
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(notice.getContent());// dirty checking
    }

    public void deleteNotice(Long noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId).orElseThrow(RuntimeException::new);// exception
        notice.setIsDeleted(true);// soft deletion
    }
}
