package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    ChooseIngredients chooseIngredients = new ChooseIngredients();

    @GetMapping
    public String index(Model model) {
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


    @GetMapping("/choose-ingredients")
    public String showIngredients(Model model){
        model.addAttribute("chooseIngredients", chooseIngredients);
        List<Ingredient> ingredients = ingredientService.getAll();

        model.addAttribute("ingredients", ingredients);

        return "choose-ingredients";
    }

    @PostMapping("/choose-ingredients")
    public String submitIngredients(@ModelAttribute("chosenIngredients") ChooseIngredients chooseIngredients) {
        return "show-recipes";
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