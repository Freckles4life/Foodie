package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.models.RecipeCreationModel;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import com.shegoestech.foodie.repository.IngredientRepository;
import com.shegoestech.foodie.repository.RecipeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientAmountsRepository ingredientAmountsRepository;

    @Getter
    @Setter
    public List<Recipe> finalMenu = new ArrayList<>();

    @Transactional
    public Recipe register(RecipeCreationModel recipe) {
        var newRecipe = new Recipe()
                .setRecipeName(recipe.getName())
                .setType(recipe.getType())
                .setRecipeInstructions(recipe.getRecipeInstruction())
                .setImage(recipe.getImage());

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

        List<Recipe> recipes = recipeRepository.findAll();

        for (Recipe r :recipes) {
            r.setEncodedImage(Base64.getEncoder().encodeToString(r.getImage()));
        }
        return recipes ;
    }



    public Recipe getById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }


    public Recipe update(Long id, Recipe recipe) {
        Recipe updatedRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        Optional<Recipe> oldRecipe = recipeRepository.findById(id);

        updatedRecipe.setRecipeName(recipe.getRecipeName());
        updatedRecipe.setType(recipe.getType());
        updatedRecipe.setRecipeInstructions(recipe.getRecipeInstructions());

        if (recipe.getImage() != null){
            updatedRecipe.setImage(recipe.getImage());
        } else {
            updatedRecipe.setImage(oldRecipe.get().getImage());
        }

        return recipeRepository.save(updatedRecipe);
    }


    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

}