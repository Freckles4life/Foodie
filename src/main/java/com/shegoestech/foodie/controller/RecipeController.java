package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private final FoodieController foodieController;


    @GetMapping("/recipe-ingredients")
    public String showIngredients(Model model){
        model.addAttribute("chooseIngredients", chooseIngredients);
        List<Ingredient> ingredients = ingredientService.getAll();

        model.addAttribute("ingredients", ingredients);

        return "recipe-ingredients";
    }


    @GetMapping("/add-recipe")
    public String addRecipeInstructions(Model map) {
        map.addAttribute("pageName", "Add Recipe Instructions");
        map.addAttribute("recipe", new Recipe());
        return "add-recipe";
    }

    @GetMapping("/success")
    public String success(Model map) {
        map.addAttribute("pageName", "You succeeded!");
        return "success";
    }


    @PostMapping
    public String saveRecipe(@Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }
        recipeService.register(recipe);
        return "success";
    }

//    @GetMapping("/menu")
//    public ModelAndView showMenu(ChooseIngredients  chooseIngredients){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("menu");
//
//        List<Recipe> recipes = recipeService.getAll();
//        List<Recipe> breakfast = recipes.stream().filter(r -> r.getType().equals("Breakfast")).collect(Collectors.toList());
//        List<Recipe> lunch = recipes.stream().filter(r-> r.getType().equals("Lunch")).collect(Collectors.toList());
//        List<Recipe> dinner = recipes.stream().filter(r-> r.getType().equals("Dinner")).collect(Collectors.toList());
//
//        Random rand = new Random();
//
//        Recipe randomBreakfast = breakfast.get(rand.nextInt(breakfast.size()));
//        Recipe randomLunch = lunch.get(rand.nextInt(lunch.size()));
//        Recipe randomDinner = dinner.get(rand.nextInt(dinner.size()));
//
//        mv.addObject("breakfast", randomBreakfast);
//        mv.addObject("breakfastIngredients", randomBreakfast.getIngredientAmounts());
//        mv.addObject("lunch", randomLunch);
//        mv.addObject("dinner", randomDinner);
//        return mv;
//    }









}