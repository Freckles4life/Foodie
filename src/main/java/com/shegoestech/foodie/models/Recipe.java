package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Table
@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "Recipe name is required")
    private String recipeName;

    @NotBlank(message = "Type is required")
    private String type;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Recipe instructions is required")
    private String recipeInstructions;

    @OneToMany(mappedBy = "ingredient")
    private Collection<IngredientAmounts> ingredientAmounts;

}
