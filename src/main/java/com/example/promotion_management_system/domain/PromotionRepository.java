package com.example.promotion_management_system.domain;

import com.example.promotion_management_system.domain.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    boolean existsByNameAndStartDateAndEndDate(String name, LocalDate startDate, LocalDate endDate);

}
