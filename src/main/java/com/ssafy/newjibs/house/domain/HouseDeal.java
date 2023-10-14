package com.ssafy.newjibs.house.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "houseinfo")
public class HouseDeal {
    @Id
    @Column(nullable = false)
    private Long no;
    @ManyToOne// N : 1 관계
    @JoinColumn(name = "aptCode")
    private HouseInfo houseInfo;
    private String dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String cancelDealType;
}
