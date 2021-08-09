package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Recipe register(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }
//
//    public Recipe getById(Long id) {
//        return recipeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Recipe not found"));
//    }
//
//    //šo iespējams nevajadzēs
//    public Recipe update(Long id, Recipe recipe) {
//        Recipe existingRecipe = recipeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Recipe not found"));
//
//        existingRecipe.setRecipeName(recipe.getRecipeName());
//        existingRecipe.setType(recipe.getType());
//        existingRecipe.setRecipeInstructions(recipe.getRecipeInstructions());
//
//        return recipeRepository.save(existingRecipe);
//    }



    //šo iespējams nevajadzēs
//    public void deleteById(Long id) {
//        recipeRepository.deleteById(id);
//    }
}
