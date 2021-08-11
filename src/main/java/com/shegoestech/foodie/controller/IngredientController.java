package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final ChooseIngredients chooseIngredients;
    private final RecipeService recipeService;

    @Autowired
    private final RecipeController recipeController;

    @GetMapping("/choose-ingredients")
    public String showIngredients(Model model){
        model.addAttribute("chooseIngredients", chooseIngredients);
        List<Ingredient> ingredients = ingredientService.getAll();

        model.addAttribute("ingredients", ingredients);

        return "choose-ingredients";
    }

    @PostMapping("/choose-ingredients")
    public ModelAndView  submitIngredients(@ModelAttribute("chosenIngredients") ChooseIngredients chooseIngredients) {
        return showMenu(chooseIngredients);
    }

//    @PostMapping
//    public String submitIngredients(@ModelAttribute("chosenIngredients") ChooseIngredients chooseIngredients){
//
//        return "menu";
//    }

    @GetMapping("/menu")
    public ModelAndView showMenu(ChooseIngredients  chooseIngredients){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu");

        List<Recipe> recipes = recipeService.getAll();
        List<Recipe> breakfast = recipes.stream().filter(r -> r.getType().equals("Breakfast")).collect(Collectors.toList());
        List<Recipe> lunch = recipes.stream().filter(r-> r.getType().equals("Lunch")).collect(Collectors.toList());
        List<Recipe> dinner = recipes.stream().filter(r-> r.getType().equals("Dinner")).collect(Collectors.toList());

        Random rand = new Random();

        Recipe randomBreakfast = breakfast.get(rand.nextInt(breakfast.size()));
        Recipe randomLunch = lunch.get(rand.nextInt(lunch.size()));
        Recipe randomDinner = dinner.get(rand.nextInt(dinner.size()));

        mv.addObject("breakfast", randomBreakfast);
        mv.addObject("breakfastIngredients", randomBreakfast.getIngredientAmounts());
        mv.addObject("lunch", randomLunch);
        mv.addObject("dinner", randomDinner);
        return mv;
    }







    //
//    @GetMapping
//    public String index(Model model) {
//        return "index";
//    }
//
//    @GetMapping("/ingredients-add")
//    public String signUp(Model map, Ingredient ingredient) {
//        map.addAttribute("pageName", "Add New ingredient");
//
//        return "ingredients-add";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteById(@PathVariable("id") Long id, Model model) {
//        ingredientService.deleteById(id);
//        return index(model);
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("pageName", "Edit New Ingredients");
//
//        Ingredient ingredient = ingredientService.getById(id);
//        model.addAttribute("ingredient", ingredient);
//
//        return "ingredient-edit";
//    }
//

    //    @PostMapping("/update/{id}")
//    public String updateIngredient(@PathVariable("id") Long id, @Valid Ingredient ingredient, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "ingredient-edit";
//        }
//
//        ingredientService.update(id, ingredient);
//
//        return index(model);
//    }

//    @PostMapping
//    public String register(@Valid Ingredient ingredient, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "ingredient-add";
//        }
//
//        ingredientService.register(ingredient);
//
//        return index(model);
//    }


}