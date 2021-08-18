package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/foodie")
public class FoodieController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;


    @GetMapping
    public String index(Model model) {
        return "index";
    }

}
