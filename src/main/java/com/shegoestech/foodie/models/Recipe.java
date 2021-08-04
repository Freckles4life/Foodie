package com.shegoestech.foodie.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = UpdateValidation.class)
    private Long id;

    @NotBlank(message = "Recipe name is required", groups = {CreateValidation.class, UpdateValidation.class})
    private String RecipeName;

    @NotBlank(message = "Type is required", groups = {CreateValidation.class, UpdateValidation.class})
    private String type;

    @NotBlank(message = "Recipe instructions is required", groups = {CreateValidation.class, UpdateValidation.class})
    private String recipeInstructions;

}
