package com.ssafy.newjibs.house.detail.houseinfo.domain;

import com.ssafy.newjibs.house.detail.housedeal.domain.HouseDeal;
import com.ssafy.newjibs.house.detail.dongcode.domain.DongCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "houseinfo")
public class HouseInfo {
    @Id
    @Column(nullable = false)
    private Long aptCode;
    @ManyToOne// N : 1 관계
    @JoinColumn(name = "dongCode")
    private DongCode dongCode;
    private Integer buildYear;
    private String roadNameBonBun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
    @OneToMany(mappedBy = "houseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HouseDeal> houseDeals;
}
