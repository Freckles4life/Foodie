package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.*;
import com.shegoestech.foodie.service.IngredientAmountsService;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private final IngredientAmountsService ingredientAmountsService;

//    @GetMapping("/recipe-ingredients")
//    public String chooseIngredientsForRecipe(Model model, Ingredient ingredient, IngredientAmounts ingredientAmounts) {
//
//        // to show all possible ingredients that you can choose from
//        List<Ingredient> ingredients = ingredientService.getAll();
//        model.addAttribute("ingredients", ingredients);
//        model.addAttribute("ingredient", ingredient);
//        model.addAttribute("ingredientAmounts", ingredientAmounts);
//
//        return "recipe-ingredients";
//    }
//
//    @PostMapping("/recipe-ingredients")
//    public String submitIngredients(Model model) {
//        recipeService.getLastId();
//
//        return "add-recipe";
//    }

    @GetMapping("/add-recipe")
    public String addRecipeInstructions(Model model, Recipe recipe) {
        List<Ingredient> ingredients = ingredientService.getAll();
        model.addAttribute("recipe", new RecipeCreationModel());
        model.addAttribute("ingredients", ingredients);
        return "add-recipe";
    }

    @PostMapping("/add-recipe")
    public String saveRecipe(@Valid RecipeCreationModel recipe, BindingResult result) {
        if (result.hasErrors()) {
            return "add-recipe";
        }

       recipeService.register(recipe);
        return "success";
    }

    @GetMapping("/success")
    public String success(Model model, Recipe recipe) {
        return "success";
    }




    @PostMapping("/success")
    public String saveRecipe2(@Valid Recipe recipe, BindingResult result, Model model) {
        return "success";
    }

    @GetMapping("/menu")
    public ModelAndView showMenu(ChooseIngredients chooseIngredients) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu");

        List<Recipe> recipes = recipeService.getAll();

        List<Recipe> filteredRecipes = recipes.stream()
                .filter(r -> r.getIngredientAmounts()
                        .stream().allMatch(i ->
                                chooseIngredients.getChosenIngredients()
                                        .contains(i.getIngredient().getId())))
                .collect(Collectors.toList());

        List<Recipe> breakfast = filteredRecipes.stream()
                .filter(r -> r.getType().equals("Breakfast"))
                .collect(Collectors.toList());

        List<Recipe> lunch = filteredRecipes.stream()
                .filter(r -> r.getType().equals("Lunch"))
                .collect(Collectors.toList());

        List<Recipe> dinner = filteredRecipes.stream()
                .filter(r -> r.getType().equals("Dinner"))
                .collect(Collectors.toList());


        Random rand = new Random();

        Recipe randomBreakfast = getRecipe(breakfast, rand);
        Recipe randomLunch = getRecipe(lunch, rand);
        Recipe randomDinner = getRecipe(dinner, rand);

        List<Recipe> recipeMenu = new ArrayList<Recipe>(Arrays.asList(randomBreakfast, randomLunch, randomDinner));
        recipeService.setFinalMenu(recipeMenu);

        mv.addObject("recipes", recipeMenu);

        return mv;
    }

    private Recipe getRecipe(List<Recipe> recipes, Random rand) {
        Recipe randomRecipe;

        if (recipes.size() > 0) {
            randomRecipe = recipes.get(rand.nextInt(recipes.size()));
        } else {
            randomRecipe =
                    new Recipe("No recipe found", new ArrayList<IngredientAmounts>());
        }
        return randomRecipe;
    }


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
        String addIngredientName=ingredient.getIngredientName().toUpperCase();
        List<Ingredient> ingredientsToCheckBeforeAdd = ingredientService.getAll()
                .stream()
                .filter(r -> r.getIngredientName().toUpperCase().equals(addIngredientName))
                .collect(Collectors.toList());

        if (ingredientsToCheckBeforeAdd.isEmpty()){
            ingredientService.register(ingredient);
            return "ingredient-success";
        }

        return "add-ingredient";
    }

    @GetMapping("/ingredient-success")
    public String successAdd(Model model, Ingredient ingredient) {
        return "ingredient-success";
    }
    @PostMapping("/ingredient-success")
    public String saveIngredient(@Valid Ingredient ingredient, BindingResult result, Model model) {
        return "ingredient-success";
    }
}