package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotBlank(message = "Recipe name is required")
    private String recipeName;

    @NotBlank(message = "Type is required")
    private String type;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Recipe instructions is required")
    private String recipeInstructions;

}
