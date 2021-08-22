package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
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
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String adminPanel() {
        return "admin";
    }


    @GetMapping("/see-ingredients")
    public String showIngredientsToAdmin(Model model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        return "see-ingredients";
    }

//
//    @GetMapping("/add-ingredient")
//    public String showIngredientAddPage(Model map, Ingredient ingredient) {
//        return "add-ingredient";
//    }
//

//    @PostMapping("/add-ingredient")
//    public String addIngredient(@Valid Ingredient ingredient, BindingResult result) {
//        if (result.hasErrors()) {
//            return "add-ingredient";
//        }
//        ingredientService.register(ingredient);
//        return "see-ingredients";
//    }
//

    @GetMapping("/add-ingredient")
    public String toAddIngredient(Model model, Ingredient ingredient) {
        model.addAttribute("ingredient", ingredient);
        return "add-ingredient";
    }

    @PostMapping("/add-ingredient")
    public String ingredientAdd(@Valid Ingredient ingredient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-ingredient";
        }

        String addIngredientName = ingredient.getIngredientName().toUpperCase();
        List<Ingredient> ingredientsToCheckBeforeAdd = ingredientService.getAll()
                .stream()
                .filter(r -> r.getIngredientName().toUpperCase().equals(addIngredientName))
                .collect(Collectors.toList());

        if (ingredientsToCheckBeforeAdd.isEmpty()) {
            ingredientService.register(ingredient);
            return showIngredientsToAdmin(model);
        }

        model.addAttribute("existingIngredientError", "Ingredient with name '" + ingredient.getIngredientName() + "' exists");
        return "add-ingredient";
    }


    @GetMapping("/edit-ingredient/{id}")
    public String editIngredientById(@PathVariable("id") Long id, Model model) {
        Ingredient ingredient = ingredientService.getById(id);
        model.addAttribute("ingredient", ingredient);
        return "edit-ingredient";
    }

    @PostMapping("/edit-ingredient/{id}")
    public String updateIngredient(@PathVariable("id") Long id, @Valid Ingredient ingredient, BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "edit-ingredient";
        }
        ingredientService.update(id, ingredient);
        return showIngredientsToAdmin(model);
    }


    @GetMapping("/see-recipes")
    public String showRecipesToAdmin(Model model) {
        model.addAttribute("recipes", recipeService.getAll());
        return "see-recipes";
    }


    @GetMapping("/edit-recipe/{id}")
    public String editById(@PathVariable("id") Long id, Model model) {
        Recipe recipe = recipeService.getById(id);
        model.addAttribute("recipe", recipe);
        return "edit-recipe";
    }

    @PostMapping("/edit-recipe/{id}")
    public String updateRecipe(@PathVariable("id") Long id, @Valid Recipe recipe, BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "edit-recipe";
        }
        recipeService.update(id, recipe);
        return showRecipesToAdmin(model);
    }

    @GetMapping("/delete-recipe/{id}")
    public String deleteRecipeById(@PathVariable("id") Long id, Model model) {
        recipeService.deleteRecipeById(id);
        return showRecipesToAdmin(model);
    }

}

