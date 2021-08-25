package com.shegoestech.foodie.repository;

import com.shegoestech.foodie.models.IngredientAmounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientAmountsRepository extends JpaRepository<IngredientAmounts, Long> {
}