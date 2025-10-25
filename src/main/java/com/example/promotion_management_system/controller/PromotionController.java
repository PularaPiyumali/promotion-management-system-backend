package com.example.promotion_management_system.controller;

import com.example.promotion_management_system.model.promotion.PromotionRequestDTO;
import com.example.promotion_management_system.model.promotion.PromotionResponseDTO;
import com.example.promotion_management_system.service.promotion.PromotionService;
import com.example.promotion_management_system.util.UriMapping;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping(UriMapping.PROMOTION_BASE_URL)
@AllArgsConstructor
@RestController
public class PromotionController {

    private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);
    private PromotionService promotionService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping(consumes = {"multipart/form-data"})
    public PromotionResponseDTO createPromotion(@Valid @ModelAttribute PromotionRequestDTO promotionDTO) throws IOException {
        logger.info("Starting to create promotion: {}", promotionDTO);
        PromotionResponseDTO response = promotionService.createPromotion(promotionDTO);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public List<PromotionResponseDTO> getAllPromotions() {
        logger.info("Fetching all promotions");
        List<PromotionResponseDTO> response = promotionService.getAllPromotions();
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public PromotionResponseDTO updatePromotion(@PathVariable Long id, @Valid @ModelAttribute PromotionRequestDTO promotionDTO) throws IOException {
        logger.info("Starting to update promotion with id {}: {}", id, promotionDTO);
        PromotionResponseDTO response = promotionService.updatePromotion(id, promotionDTO);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public PromotionResponseDTO deletePromotion(@PathVariable Long id) {
        logger.info("Deleting promotion with id: {}", id);
        PromotionResponseDTO response = promotionService.deletePromotion(id);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public PromotionResponseDTO getPromotionById(@PathVariable Long id) {
        logger.info("Fetching promotion with id: {}", id);
        return promotionService.getPromotionById(id);
    }
}
