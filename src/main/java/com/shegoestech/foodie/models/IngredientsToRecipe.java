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

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotBlank(message = "Recipe id is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @NotBlank(message = "Ingredient id is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Ingredient ingredient;

    @NotBlank(message = "Amount is required", groups = {CreateValidation.class, UpdateValidation.class})
    private Long amount;
}
