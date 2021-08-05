package com.shegoestech.foodie.repository;

import com.shegoestech.foodie.models.IngredientsToRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface IngredientsToRecipeRepository extends JpaRepository<IngredientRepository, Long> {
public interface IngredientsToRecipeRepository extends JpaRepository<IngredientsToRecipe, Long> {
}
