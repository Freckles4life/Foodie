package com.shegoestech.foodie.models;

import lombok.Data;

import java.util.List;

@Data
public class RecipeCreationModel {
    private List<String> ingredient;
    private List<Long> amount;
    private String name;
    private String type;
    private String recipeInstruction;
    private byte[] image;
}
