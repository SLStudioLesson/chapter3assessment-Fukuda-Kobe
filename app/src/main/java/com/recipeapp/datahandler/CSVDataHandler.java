package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model1.Ingredient;
import com.recipeapp.model1.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }
    // 設問5 readDataメソッドの実装
    // 型に気を付けて返り値を用意し、返す
    @Override
    public ArrayList<Recipe> readData() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        // fileの読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // 1行ずつ処理を実行する
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                // 読み取った行を、料理名と材料,材料、材料に分割して配列に格納する
                String[] rows = line.split(",");
                // 料理名は名前に代入 インデックス0の要素は料理名となる
                String name = rows[0];
                // 材料はそれぞれIngredientクラスに材料名を渡して、インスタンスを生成する。
                for (int i = 1; i < rows.length; i++) {
                    Ingredient ing = new Ingredient(rows[i]);
                    ingredients.add(ing);
                }
                // Recipeクラスにnameとingredientsを渡す
                Recipe re = new Recipe(name, ingredients);
                // RecipeインスタンスをArrayList<Recipe> recipesに格納する
                recipes.add(re);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) {

    }
    @Override
    public ArrayList<Recipe> searchData() {
        return null;
    }
}
