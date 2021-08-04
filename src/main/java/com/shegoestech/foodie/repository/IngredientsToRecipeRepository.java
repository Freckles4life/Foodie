package com.shegoestech.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsToRecipeRepository extends JpaRepository<IngredientRepository, Long> {
}
