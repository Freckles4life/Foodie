package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.repository.IngredientAmountsRepository;
import com.shegoestech.foodie.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public Ingredient register(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }


    public List<Ingredient> getAll() {
        return ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "ingredientName"));
    }

}