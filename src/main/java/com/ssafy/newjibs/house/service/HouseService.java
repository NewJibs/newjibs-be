package com.ssafy.newjibs.house.service;

import com.ssafy.newjibs.house.repository.DongCodeRepository;
import com.ssafy.newjibs.house.repository.HouseDealRepository;
import com.ssafy.newjibs.house.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
    private final DongCodeRepository dongcodeRepository;
    private final HouseInfoRepository houseInfoRepository;
    private final HouseDealRepository houseDealRepository;


}
