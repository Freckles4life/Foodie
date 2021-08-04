package com.shegoestech.foodie.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Ingredient implements Serializable {

    public Ingredient(Long id, String name) {
        this.id = id;
        Name = name;
        //
    }

    @NotNull()
    private Long id;
    @NotBlank()
    private String Name;
}
