package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientAmountsService {
    private final IngredientAmountsRepository ingredientAmountsRepository;

    public IngredientAmounts register(IngredientAmounts ingredientAmounts){
        return ingredientAmountsRepository.save(ingredientAmounts);
    }
}
