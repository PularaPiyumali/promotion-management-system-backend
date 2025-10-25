package com.example.promotion_management_system.model.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionResponseDTO {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String bannerImageUrl;
    private String message;
}
