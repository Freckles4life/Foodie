package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.IngredientAmounts;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientAmountsService;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import com.shegoestech.foodie.service.ShoppingListExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final ChooseIngredients chooseIngredients;
   @Autowired
    private final RecipeService recipeService;

    @Autowired
    private final RecipeController recipeController;
    private final IngredientAmountsService ingredientAmountsService;

    @GetMapping("/choose-ingredients")
    public String showIngredients(Model model) {
        model.addAttribute("chooseIngredients", chooseIngredients);
        List<Ingredient> ingredients = ingredientService.getAll();
        model.addAttribute("ingredients", ingredients);
        return "choose-ingredients";
    }

    @PostMapping("/choose-ingredients")
    public ModelAndView submitIngredients(@ModelAttribute("chosenIngredients") ChooseIngredients chooseIngredients) {
        return recipeController.showMenu(chooseIngredients);
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
            return "success";
        }

        return "add-ingredient";
    }

    @GetMapping("/success")
    public String successAdd(Model model, Ingredient ingredient) {
        return "success";
    }
    @PostMapping("/success")
    public String saveIngredient(@Valid Ingredient ingredient, BindingResult result, Model model) {
        return "success";
    }
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

    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=shopping_list_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);
        List<Recipe> randomMenu = recipeService.getFinalMenu();

        ShoppingListExporter exporter = new ShoppingListExporter(randomMenu);
        exporter.export(response);

    }
}