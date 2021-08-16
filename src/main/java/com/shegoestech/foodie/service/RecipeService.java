package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.models.RecipeCreationModel;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import com.shegoestech.foodie.repository.IngredientRepository;
import com.shegoestech.foodie.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientAmountsRepository ingredientAmountsRepository;

    public Recipe register(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe register(RecipeCreationModel recipe) {
        var newRecipe = new Recipe()
                .setRecipeName(recipe.getName())
                .setType(recipe.getType())
                .setRecipeInstructions(recipe.getRecipeInstruction());

        var savedRecipe = recipeRepository.save(newRecipe);

        List<IngredientAmounts> ingredientAmounts = new ArrayList<>();
        for (int i = 0; i < recipe.getIngredient().size(); i++) {
            String ingredientName = recipe.getIngredient().get(i);
            Long amount = recipe.getAmount().get(i);

            var ingredient = ingredientRepository.findByIngredientNameIgnoreCase(ingredientName);
            if (ingredient.isEmpty())
                continue;

            var ingredientAmount = IngredientAmounts.builder()
                    .amount(amount)
                    .ingredient(ingredient.get())
                    .recipe(savedRecipe)
                    .build();

            ingredientAmounts.add(ingredientAmount);
        }

        ingredientAmountsRepository.saveAll(ingredientAmounts);

        return recipeRepository.getById(savedRecipe.getId());
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Long getLastId() {
        return recipeRepository.count();

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
