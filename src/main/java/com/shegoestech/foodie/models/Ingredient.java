package com.shegoestech.foodie.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
