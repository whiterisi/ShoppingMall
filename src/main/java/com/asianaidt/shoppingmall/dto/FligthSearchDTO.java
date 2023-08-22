package com.asianaidt.shoppingmall.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("FlightSearchDTO")
public class FligthSearchDTO {
    private String schDeptCityCode;
    private String schArrvCityCode;
    private String schDate;

}
