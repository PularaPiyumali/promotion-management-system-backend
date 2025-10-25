package com.example.promotion_management_system.service.promotion;

import com.example.promotion_management_system.domain.promotion.Promotion;
import com.example.promotion_management_system.model.promotion.PromotionRequestDTO;
import com.example.promotion_management_system.model.promotion.PromotionResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromotionMapper {

    /**
     * To promotion promotion.
     *
     * @param dto the dto
     * @return the promotion
     */
    public static Promotion toPromotion(PromotionRequestDTO dto) {
        Promotion promotion = new Promotion();
        promotion.setName(dto.getName());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setBannerImagePath(dto.getBanner().getOriginalFilename());
        return promotion;
    }

    /**
     * To promotions list.
     *
     * @param promotionRequestDTOS the promotion request dtos
     * @return the list
     */
    public static List<Promotion> toPromotions(List<PromotionRequestDTO> promotionRequestDTOS) {
        List<Promotion> promotions = new ArrayList<>();
        promotionRequestDTOS.forEach(userRequestDTO -> {
            promotions.add(toPromotion(userRequestDTO));
        });
        return promotions;
    }

    /**
     * To promotion response dto promotion response dto.
     *
     * @param promotion the promotion
     * @return the promotion response dto
     */
    public static PromotionResponseDTO toPromotionResponseDto(Promotion promotion) {
        PromotionResponseDTO dto = new PromotionResponseDTO();
        dto.setId(promotion.getId());
        dto.setName(promotion.getName());
        dto.setStartDate(promotion.getStartDate());
        dto.setEndDate(promotion.getEndDate());
        dto.setBannerImageUrl("/uploads/promotions/" + promotion.getBannerImagePath());
        return dto;
    }

    /**
     * To promotion response dto list list.
     *
     * @param promotion the promotion
     * @return the list
     */
    public static List<PromotionResponseDTO> toPromotionResponseDtoList(List<Promotion> promotion) {
        List<PromotionResponseDTO> promotionResponseDTOS = new ArrayList<>();
        promotion.forEach(user -> {
            promotionResponseDTOS.add(toPromotionResponseDto(user));
        });

        return promotionResponseDTOS;
    }
}
