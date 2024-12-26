package com.recipeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model1.Ingredient;
import com.recipeapp.model1.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    //設問5 displayRecipesメソッドの実装
    private void displayRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>(dataHandler.readData()); // 選択されたファイル形式のArrayList<Recipe> recipesを受け取る
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        // recipesの中身が何もないとき、メッセージを出力して処理を終了する
        if (recipes.size() == 0) {
            System.out.println("No recipes available.");
            return;
        }
        // recipeに要素があれば、下記の処理を実行する
        System.out.println("Recipes:");
        for (Recipe recipe : recipes) { // recipesに格納されている各インスタンスに対して、下記の処理を実行する
            System.out.println("-----------------------------------");
            System.out.println("Recipe Name: " + recipe.getName());
            System.out.print("Main Ingredients: ");
            ingredients = recipe.getIngredients();
            for (Ingredient ingredient : ingredients) {
                    System.out.print(ingredient.getName() + ","); // Ingredientsのjつ目の要素のインスタンスに対して、getName()を実行

                }
            System.out.println();
            }
        }
        // recipesの中身は、Recipe型のインスタンスが格納されている
        // 料理名:String nameで格納。具材はArrayList<Ingredient> ingredientsという名前で格納されている
}

