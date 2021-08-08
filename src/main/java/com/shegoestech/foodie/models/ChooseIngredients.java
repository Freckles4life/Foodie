package com.shegoestech.foodie.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChooseIngredients {

    @Getter()
    @Setter()
    private List<Long> chosenIngredients = new ArrayList<Long>();
}
