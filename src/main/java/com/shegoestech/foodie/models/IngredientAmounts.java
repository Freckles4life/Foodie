package com.shegoestech.foodie.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity
@Data
public class IngredientAmounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
