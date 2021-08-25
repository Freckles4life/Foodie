package com.shegoestech.foodie.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChooseIngredients {

    @Getter()
    @Setter()
    private List<Long> chosenIngredients = new ArrayList<Long>();
}