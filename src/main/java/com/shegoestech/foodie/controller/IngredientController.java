package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;


@Controller
@ControllerAdvice
public class IngredientController {


    @GetMapping("/all")
    public String getAll(Model model) {

        Ingredient egg = new Ingredient(1L, "Egg");
        Ingredient milk = new Ingredient(2L, "Milk");

        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();

        allIngredients.add(egg);
        allIngredients.add(milk);

        model.addAttribute("ingredient", allIngredients);
        return "index.html";
    }
}
