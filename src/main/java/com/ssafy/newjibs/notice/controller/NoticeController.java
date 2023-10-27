package com.ssafy.newjibs.notice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notices")
public class NoticeController {
	private final NoticeService noticeService;

	@PostMapping
	public ResponseEntity<Void> saveNotice(@RequestBody NoticeDto noticeDto) {
		noticeService.createNotice(noticeDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{noticeId}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable Long noticeId) {
		return ResponseEntity.ok(noticeService.readNotice(noticeId));
	}

	@GetMapping
	public ResponseEntity<List<NoticeListDto>> getAllNotice() {
		return ResponseEntity.ok(noticeService.readAllNotice());
	}

	@PatchMapping("/{noticeId}")
	public ResponseEntity<Void> modifyNotice(@PathVariable Long noticeId, @RequestBody NoticeDto noticeDto) {
		noticeService.updateNotice(noticeId, noticeDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{noticeId}")
	public ResponseEntity<Void> removeNotice(@PathVariable Long noticeId) {
		noticeService.deleteNotice(noticeId);
		return ResponseEntity.ok().build();
	}
}
