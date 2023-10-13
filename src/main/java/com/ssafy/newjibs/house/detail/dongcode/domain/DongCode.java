package com.ssafy.newjibs.house.detail.dongcode.domain;

import com.ssafy.newjibs.house.detail.houseinfo.domain.HouseInfo;
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
@Table(name = "dongcode")
public class DongCode {
    @Id
    @Column(nullable = false)
    private String dongCode;// use api inner id as a pk
    private String sidoName;
    private String gugunName;
    private String dongName;
    @OneToMany(mappedBy = "dongCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HouseInfo> houseInfos;
}
