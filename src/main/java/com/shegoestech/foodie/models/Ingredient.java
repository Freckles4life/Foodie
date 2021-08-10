package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Table
@Entity
@Data
public class Ingredient /*implements Serializable*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotBlank()
    private String ingredientName;

    @NotBlank()
    private String ingredientMeasure;

    @OneToMany(mappedBy = "recipe")
    private Collection<IngredientAmounts> ingredientAmounts;
}
