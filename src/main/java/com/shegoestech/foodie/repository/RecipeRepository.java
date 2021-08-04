package com.shegoestech.foodie.repository;

import com.shegoestech.foodie.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
