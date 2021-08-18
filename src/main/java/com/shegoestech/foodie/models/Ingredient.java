package com.shegoestech.foodie.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Table
@Entity
@Data
public class Ingredient /*implements Serializable*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Column(unique = true)
    private String ingredientName;

    @NotBlank()
    private String ingredientMeasure;

    @OneToMany(mappedBy = "ingredient")
    private Collection<IngredientAmounts> ingredientAmounts;

    public Ingredient(){};
}
