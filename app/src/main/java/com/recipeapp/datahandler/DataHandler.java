package com.recipeapp.datahandler;

import java.util.ArrayList;

import com.recipeapp.model1.Recipe;

public interface DataHandler {
    public String getMode();
    public ArrayList<Recipe> readData();
    public void writeData(Recipe recipe);
    public ArrayList<Recipe> searchData();
}