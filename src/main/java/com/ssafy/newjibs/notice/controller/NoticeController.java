package com.ssafy.newjibs.notice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notices")
public class NoticeController {
	private final NoticeService noticeService;

	@ApiOperation(value = "공지사항을 저장한다.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> saveNotice(@RequestBody NoticeDto noticeDto) {
		noticeService.createNotice(noticeDto);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "특정 공지사항을 읽어온다.")
	@GetMapping("/{noticeId}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable Long noticeId) {
		return ResponseEntity.ok(noticeService.readNotice(noticeId));
	}

	@ApiOperation(value = "모든 공지사항을 읽어온다.")
	@GetMapping
	public ResponseEntity<List<NoticeListDto>> getAllNotice() {
		return ResponseEntity.ok(noticeService.readAllNotice());
	}

	@ApiOperation(value = "공지사항을 수정한다.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{noticeId}")
	public ResponseEntity<Void> modifyNotice(@PathVariable Long noticeId, @RequestBody NoticeDto noticeDto) {
		noticeService.updateNotice(noticeId, noticeDto);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "공지사항을 삭제한다.")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{noticeId}")
	public ResponseEntity<Void> removeNotice(@PathVariable Long noticeId) {
		noticeService.deleteNotice(noticeId);
		return ResponseEntity.ok().build();
	}
}
