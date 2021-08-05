package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity
@Data
public class IngredientsToRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = UpdateValidation.class)
    private Long id;

    @NotBlank(message = "Recipe id is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Long RecipeId;

    @NotBlank(message = "Ingredient id is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Long IngredientId;

    @NotBlank(message = "Amount is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Long Amount;
}
