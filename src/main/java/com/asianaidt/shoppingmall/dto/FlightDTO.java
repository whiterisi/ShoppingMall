package com.asianaidt.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
        private String internationalNum;
        private String airport;
        private String city;
        private String internationalTime;
        private String internationalIoType;

}
