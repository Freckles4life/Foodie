package com.shegoestech.foodie.controller;

import com.shegoestech.foodie.models.ChooseIngredients;
import com.shegoestech.foodie.models.Ingredient;
import com.shegoestech.foodie.models.Recipe;
import com.shegoestech.foodie.service.IngredientService;
import com.shegoestech.foodie.service.RecipeService;
import com.shegoestech.foodie.service.ShoppingListExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    @GetMapping("/ingredient-success")
    public String successAdd(Model model, Ingredient ingredient) {
        return "ingredient-success";
    }


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