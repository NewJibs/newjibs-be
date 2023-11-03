package com.ssafy.newjibs.house.controller;

import com.ssafy.newjibs.api.ApiExplorer;
import com.ssafy.newjibs.api.house.HouseApi;
import com.ssafy.newjibs.house.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/houses")
public class HouseController {
    private final HouseService houseService;
    private final HouseApi houseApi;

    @GetMapping("/api/test")
    public ResponseEntity<Void> apiTest() throws IOException {
        String data = ApiExplorer.getData(houseApi.getHouseDealUrl(11110, 	201512));
        System.out.println(data);

        return null;
    }
}
