package com.shegoestech.foodie.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Table
@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String ingredientName;

    @NotBlank
    private String ingredientMeasure;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private Collection<IngredientAmounts> ingredientAmounts;

    public Ingredient() {
    }

}
