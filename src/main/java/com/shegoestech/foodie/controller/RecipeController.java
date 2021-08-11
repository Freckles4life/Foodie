package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;


    @GetMapping("/recipe-ingredients")
    public String chooseIngredientsForRecipe(Model model, Recipe recipe){
        return "recipe-ingredients";
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


    // will save all chosen ingredients in the list
//    @PostMapping("/recipe-ingredients")
//    public String saveIngredientsForRecipe(Model model) {
//        return "recipe-ingredients";
//    }


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