package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table
@Entity
@Data
public class Ingredient /*implements Serializable*/ {

    /*public Ingredient(Long id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
        //
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = UpdateValidation.class)
    private Long id;

    @NotBlank()
    private String ingredientName;
}
