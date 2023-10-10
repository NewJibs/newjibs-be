package com.ssafy.newjibs.notice.repository;

import com.ssafy.newjibs.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeId(Long noticeId);

    List<Notice> findAll();
}
