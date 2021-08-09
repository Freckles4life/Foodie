package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final ChooseIngredients chooseIngredients;


    @GetMapping("/choose-ingredients-2")
    public String showIngredients(Model model){
        model.addAttribute("chooseIngredients", chooseIngredients);
        List<Ingredient> ingredients = ingredientService.getAll();

        model.addAttribute("ingredients", ingredients);

        return "choose-ingredients-2";
    }

    @GetMapping("/add-recipe")
    public String signUp(Model map, Recipe recipe) {
        map.addAttribute("pageName", "Add New Recipe");

        return "add-recipe";
    }


    @PostMapping
    public String register(@Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }

        recipeService.register(recipe);

        return "success";
    }

    @GetMapping("/show-recipes")
    public String showRecipes( Model model, @ModelAttribute("chosenIngredients") ChooseIngredients chooseIngredients){
        List<Recipe> recipes = recipeService.getAll();
        List<Recipe> breakfast = recipes.stream().filter(r-> r.getType() == "Breakfast").collect(Collectors.toList());
        List<Recipe> lunch = recipes.stream().filter(r-> r.getType() == "Lunch").collect(Collectors.toList());
        List<Recipe> dinner = recipes.stream().filter(r-> r.getType() == "Dinner").collect(Collectors.toList());

        Random rand = new Random();
        Recipe randomBreakfast = breakfast.get(rand.nextInt(breakfast.size()));
        Recipe randomLunch = lunch.get(rand.nextInt(lunch.size()));
        Recipe randomDinner = dinner.get(rand.nextInt(dinner.size()));

        model.addAttribute("breakfast", randomBreakfast);
        model.addAttribute("lunch", randomLunch);
        model.addAttribute("dinner", randomDinner);
        return "show-recipes";
    }









    //    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("recipes", recipeService.getAll());
//        return "index";
////    }
//    @GetMapping("/delete/{id}")
//    public String deleteById(@PathVariable("id") Long id, Model model) {
//        recipeService.deleteById(id);
//        return index(model);
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("pageName", "Edit New Recipe");
//
//        Recipe recipe = recipeService.getById(id);
//        model.addAttribute("recipes", recipe);
//
//        return "recipe-edit";
//    }


//
//    @PostMapping("/update/{id}")
//    public String updateRecipe(@PathVariable("id") Long id, @Valid Recipe recipe, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "recipe-edit";
//        }
//
//        recipeService.update(id, recipe);
//
//        return index(model);
//    }

}