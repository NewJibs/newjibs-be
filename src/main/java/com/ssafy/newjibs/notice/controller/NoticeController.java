package com.ssafy.newjibs.notice.controller;

import com.ssafy.newjibs.notice.dto.NoticeDto;
import com.ssafy.newjibs.notice.dto.NoticeListDto;
import com.ssafy.newjibs.notice.service.NoticeService;
import com.ssafy.newjibs.response.BaseResponse;
import com.ssafy.newjibs.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public BaseResponse<Void> saveNotice(@RequestBody NoticeDto noticeDto) {
        noticeService.createNotice(noticeDto);
        return new BaseResponse<>(ResponseStatus.CREATED);
    }

    @GetMapping("/{noticeId}")
    public BaseResponse<NoticeDto> getNotice(@PathVariable Long noticeId) {
        NoticeDto noticeDto = noticeService.readNotice(noticeId);
        return new BaseResponse<>(ResponseStatus.SUCCESS, noticeDto);
    }

    @GetMapping
    public BaseResponse<List<NoticeListDto>> getAllNotice() {
        List<NoticeListDto> list = noticeService.readAllNotice();
        return new BaseResponse<>(ResponseStatus.SUCCESS, list);
    }

    @PatchMapping("/{noticeId}")
    public BaseResponse<Void> modifyNotice(@PathVariable Long noticeId, NoticeDto noticeDto) {
        noticeService.updateNotice(noticeId, noticeDto);
        return new BaseResponse<>(ResponseStatus.SUCCESS);
    }

    @DeleteMapping("/{noticeId}")
    public BaseResponse<Void> removeNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return new BaseResponse<>(ResponseStatus.SUCCESS);
    }
}
