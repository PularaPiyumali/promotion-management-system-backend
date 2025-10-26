package com.example.promotion_management_system.service.promotion;

import com.example.promotion_management_system.model.promotion.PromotionRequestDTO;
import com.example.promotion_management_system.model.promotion.PromotionResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PromotionService {

    PromotionResponseDTO createPromotion(PromotionRequestDTO dto) throws IOException;

    /**
     * Gets all promotions.
     *
     * @return the all promotions
     */
    List<PromotionResponseDTO> getAllPromotions();

    /**
     * Update promotion response dto.
     *
     * @param id  the id
     * @param dto the dto
     * @return the promotion response dto
     * @throws IOException the io exception
     */
    @Transactional
    PromotionResponseDTO updatePromotion(Long id, PromotionRequestDTO dto) throws IOException;

    /**
     * Delete promotion response dto.
     *
     * @param id the id
     * @return the promotion response dto
     */
    PromotionResponseDTO deletePromotion(Long id);

    /**
     * Gets promotion by id.
     *
     * @param id the id
     * @return the promotion by id
     */
    PromotionResponseDTO getPromotionById(Long id);


    /**
     * Save banner file.
     *
     * @param banner the banner
     * @return the string
     * @throws IOException the io exception
     */
    String saveBannerFile(MultipartFile banner) throws IOException;
}
