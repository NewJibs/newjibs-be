package com.ssafy.newjibs.house.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseRequestDto {
    @JsonProperty("city")
    private String city;

    @JsonProperty("dongcode")
    private String dongcode;

    @JsonProperty("dong")
    private String dong;
}
