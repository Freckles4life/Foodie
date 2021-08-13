package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
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
    private final ChooseIngredients chooseIngredients;

    @GetMapping("/recipe-ingredients")
    public String chooseIngredientsForRecipe(Model model){
      List<Ingredient> ingredients = ingredientService.getAll();
       model.addAttribute("ingredients", ingredients);

        return "recipe-ingredients";
    }

    @PostMapping("/recipe-ingredients")
    public String submitIngredients(Model model, Recipe recipes) {
        return "add-recipe";
    }

    @GetMapping("/add-recipe")
    public String addRecipeInstructions(Model model, Recipe recipe) {
        model.addAttribute("recipe", new Recipe());
        return "add-recipe";
    }

    @GetMapping("/success")
    public String success(Model model, Recipe recipe) {
        return "success";
    }

    @GetMapping("/menu-test")
    public String testMenu(Model model, Recipe recipe) {
        return "menu-test";
    }




    @PostMapping("/add-recipe")
    public String saveRecipe(@Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }
        recipeService.register(recipe);
        return "success";
    }

    @PostMapping("/success")
    public String saveRecipe2(@Valid Recipe recipe, BindingResult result, Model model) {
         return "success";
    }

    @GetMapping("/menu")
    public ModelAndView showMenu(ChooseIngredients chooseIngredients){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu");

        List<Recipe> recipes = recipeService.getAll();

        List<Recipe> filteredRecipes = recipes.stream()
                .filter(r -> r.getIngredientAmounts()
                        .stream().noneMatch(i ->
                                chooseIngredients.getChosenIngredients()
                                        .contains(i.getIngredient().getId())))
                .collect(Collectors.toList());

        List<Recipe> breakfast = filteredRecipes.stream()
                .filter(r -> r.getType().equals("Breakfast"))
                .collect(Collectors.toList());

        List<Recipe> lunch = filteredRecipes.stream()
                .filter(r-> r.getType().equals("Lunch"))
                .collect(Collectors.toList());

        List<Recipe> dinner = filteredRecipes.stream()
                .filter(r-> r.getType().equals("Dinner"))
                .collect(Collectors.toList());


        Random rand = new Random();

        Recipe randomBreakfast = getRecipe(breakfast, rand);
        Recipe randomLunch = getRecipe(lunch, rand);
        Recipe randomDinner = getRecipe(dinner, rand);

        List<Recipe> recipeMenu = new ArrayList<Recipe>(Arrays.asList(randomBreakfast,randomLunch, randomDinner));

        mv.addObject("recipes",recipeMenu);

        return mv;
    }

    private Recipe getRecipe(List<Recipe> recipes, Random rand) {
        Recipe randomRecipe;

        if(recipes.size() > 0)
        {
            randomRecipe = recipes.get(rand.nextInt(recipes.size()));
        }
        else
        {
            randomRecipe =
                    new Recipe("No recipe found", new ArrayList<IngredientAmounts>());
        }
        return randomRecipe;
    }

}