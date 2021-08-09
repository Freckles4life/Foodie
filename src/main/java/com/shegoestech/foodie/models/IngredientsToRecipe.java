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
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotBlank
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @NotBlank
    private Ingredient ingredient;

    @NotNull
    private Long amount;
}
