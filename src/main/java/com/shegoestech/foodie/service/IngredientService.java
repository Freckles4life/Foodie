package com.shegoestech.foodie.service;

import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
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

    public Ingredient update(Long id, Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        existingIngredient.setIngredientName(ingredient.getIngredientName());

        return ingredientRepository.save(existingIngredient);
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}