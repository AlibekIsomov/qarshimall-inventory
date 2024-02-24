package com.bim.inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsItemDTO {

    private String description;

    private String size;

    private String location;

    private Long fileEntityId;
}
