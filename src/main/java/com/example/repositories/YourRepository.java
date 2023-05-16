package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.YourEntity;

@Repository
public interface YourRepository extends JpaRepository<YourEntity, Long> {
    YourEntity findByCategoryCode(String categoryCode);
}
