package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        return "index";
    }


    @GetMapping("/ingredients-add")
    public String signUp(Model map, Ingredient ingredient) {
        map.addAttribute("pageName", "Add New ingredient");

        return "ingredients-add";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        ingredientService.deleteById(id);
        return index(model);
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New Ingredients");

        Ingredient ingredient = ingredientService.getById(id);
        model.addAttribute("ingredient", ingredient);

        return "ingredient-edit";
    }

    @PostMapping
    public String register(@Valid Ingredient ingredient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ingredient-add";
        }

        ingredientService.register(ingredient);

        return index(model);
    }

    @PostMapping("/update/{id}")
    public String updateIngredient(@PathVariable("id") Long id, @Valid Ingredient ingredient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ingredient-edit";
        }

        ingredientService.update(id, ingredient);

        return index(model);
    }
}