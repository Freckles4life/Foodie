package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientAmountsService {
    private final IngredientAmountsRepository ingredientAmountsRepository;


    public List<IngredientAmounts> getAllRecipeIngredients(Long recipeId) {
          return ingredientAmountsRepository.findAllByRecipeId(recipeId);
    }

}
