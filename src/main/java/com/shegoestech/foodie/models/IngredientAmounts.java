package com.shegoestech.foodie.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Data
@Builder
public class IngredientAmounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @NotNull
    private Ingredient ingredient;

    @NotNull
    private Long amount;
}