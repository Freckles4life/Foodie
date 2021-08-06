package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.RecipeService;
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
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.getAll());
        return "index";
    }

    @GetMapping("/add-recipe")
    public String signUp(Model map, Recipe recipe) {
        map.addAttribute("pageName", "Add New Recipe");

        return "recipe-add";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        recipeService.deleteById(id);
        return index(model);
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Edit New Recipe");

        Recipe recipe = recipeService.getById(id);
        model.addAttribute("recipes", recipe);

        return "recipe-edit";
    }

    @PostMapping
    public String register(@Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }

        recipeService.register(recipe);

        return index(model);
    }

    @PostMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") Long id, @Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "recipe-edit";
        }

        recipeService.update(id, recipe);

        return index(model);
    }
}