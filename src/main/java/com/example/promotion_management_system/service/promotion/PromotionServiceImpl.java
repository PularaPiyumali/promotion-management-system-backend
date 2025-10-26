package com.example.promotion_management_system.service.promotion;

import com.example.promotion_management_system.domain.PromotionRepository;
import com.example.promotion_management_system.domain.promotion.Promotion;
import com.example.promotion_management_system.model.promotion.PromotionRequestDTO;
import com.example.promotion_management_system.model.promotion.PromotionResponseDTO;
import com.example.promotion_management_system.util.exception.PromotionManagementSystemException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PromotionServiceImpl implements PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    private final String UPLOAD_DIR = "uploads/promotions/";
    private PromotionRepository promotionRepository;

    /**
     * Create promotion response dto.
     *
     * @param dto the dto
     * @return the promotion response dto
     * @throws IOException the io exception
     */
    @Transactional
    @Override
    public PromotionResponseDTO createPromotion(PromotionRequestDTO dto) throws IOException {
        logger.info("Creating promotion: {}", dto);
        if (promotionRepository.existsByNameAndStartDateAndEndDate(
                dto.getName(),
                dto.getStartDate(),
                dto.getEndDate()
        )) {
            logger.error("Promotion already exists with name {} and date range {} - {}", dto.getName(), dto.getStartDate(), dto.getEndDate());
            throw new PromotionManagementSystemException("A promotion with the same title and date range already exists!",
                    HttpStatus.BAD_REQUEST.value()
            );
        }

        Promotion promotion = PromotionMapper.toPromotion(dto);
        promotion.setBannerImagePath(saveBannerFile(dto.getBanner()));

        Promotion saved = promotionRepository.save(promotion);

        PromotionResponseDTO response = PromotionMapper.toPromotionResponseDto(saved);
        response.setMessage("Promotion created successfully!");
        logger.info("Successfully created promotion: {}", response);
        return response;
    }

    /**
     * Gets all promotions.
     *
     * @return the all promotions
     */
    @Override
    public List<PromotionResponseDTO> getAllPromotions() {
        logger.info("Fetching all promotions");
        List<Promotion> promotions = promotionRepository.findAll();
        return PromotionMapper.toPromotionResponseDtoList(promotions);
    }

    /**
     * Update promotion response dto.
     *
     * @param id  the id
     * @param dto the dto
     * @return the promotion response dto
     * @throws IOException the io exception
     */
    @Transactional
    @Override
    public PromotionResponseDTO updatePromotion(Long id, PromotionRequestDTO dto) throws IOException {
        logger.info("Updating promotion with id {}: {}", id, dto);
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Promotion not found with id: {}", id);
                    return new PromotionManagementSystemException("Promotion not found", HttpStatus.BAD_REQUEST.value());
                });
        promotion.setName(dto.getName());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());

        MultipartFile banner = dto.getBanner();
        if (banner != null && !banner.isEmpty()) {
            promotion.setBannerImagePath(saveBannerFile(banner));
        }

        Promotion updated = promotionRepository.save(promotion);

        PromotionResponseDTO response = PromotionMapper.toPromotionResponseDto(updated);
        response.setMessage("Promotion updated successfully!");
        logger.info("Successfully updated promotion: {}", response);
        return response;
    }

    /**
     * Delete promotion response dto.
     *
     * @param id the id
     * @return the promotion response dto
     */
    @Override
    public PromotionResponseDTO deletePromotion(Long id) {
        logger.info("Deleting promotion with id: {}", id);
        promotionRepository.deleteById(id);
        PromotionResponseDTO response = new PromotionResponseDTO();
        response.setMessage("Promotion deleted successfully!");
        logger.info("Successfully deleted promotion with id: {}", id);
        return response;
    }

    /**
     * Gets promotion by id.
     *
     * @param id the id
     * @return the promotion by id
     */
    @Override
    public PromotionResponseDTO getPromotionById(Long id) {
        logger.info("Fetching promotion with id: {}", id);
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Promotion not found with id: {}", id);
                    return new PromotionManagementSystemException("Promotion not found", HttpStatus.NOT_FOUND.value());
                });
        PromotionResponseDTO response = PromotionMapper.toPromotionResponseDto(promotion);
        logger.info("Successfully fetched promotion: {}", response);
        return response;
    }


    /**
     * Save banner file.
     *
     * @param banner the banner
     * @return the string
     * @throws IOException the io exception
     */
    @Override
    public String saveBannerFile(MultipartFile banner) throws IOException {
        if (banner == null || banner.isEmpty()) {
            return null;
        }

        String filename = UUID.randomUUID() + "_" + banner.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR, filename);
        Files.createDirectories(path.getParent());
        Files.copy(banner.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        logger.info("Saved banner file: {}", filename);
        return filename;
    }

}
