package com.ssafy.newjibs.house.controller;

import com.ssafy.newjibs.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/houses")
public class HouseController {
    private final HouseService houseService;

}
