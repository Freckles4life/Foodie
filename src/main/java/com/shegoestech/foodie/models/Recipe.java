package com.shegoestech.foodie.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Accessors(chain = true)
@Table
@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Recipe name is required")
    private String recipeName;

    @NotBlank(message = "Type is required")
    private String type;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Recipe instructions is required")
    private String recipeInstructions;

    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @Transient
    private String encodedImage;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
    private Collection<IngredientAmounts> ingredientAmounts;

    public Recipe()
    {
    }

    public Recipe(String recipeName, Collection<IngredientAmounts> ingredientAmounts) {
        this.recipeName = recipeName;
        this.ingredientAmounts = ingredientAmounts;
    }

}
