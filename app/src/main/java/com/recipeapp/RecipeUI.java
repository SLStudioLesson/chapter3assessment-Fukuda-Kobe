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
                        addNewRecipe();
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

    // 設問5 displayRecipesメソッドの実装
    private void displayRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>(dataHandler.readData()); // 選択されたファイル形式のArrayList<Recipe> recipesを受け取る
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        // recipesの要素が空のとき、メッセージを出力して処理を終了する
        if (recipes.size() == 0) {
            System.out.println("No recipes available.");
            return;
        }
        // recipeに要素があれば、下記の処理を実行する
        System.out.println("Recipes:");
        for (Recipe recipe : recipes) { // recipesに格納されている各インスタンスに対して、下記の処理を実行する
            System.out.println("-----------------------------------");
            System.out.println("Recipe Name: " + recipe.getName()); // recipeインスタンスのgetNameメソッドを利用
            System.out.print("Main Ingredients: ");
            ingredients = recipe.getIngredients(); // ArrayList<Ingredient> ingredientsを、recipeインスタンスのgetIngredientsメソッドで受け取る
            for (int i = 0; i <= ingredients.size() - 1; i++) { // Ingredientsの各要素に対して、下記の処理を実行する
                // ArayList<Ingreditent> ingredientsに対してインデックスiの要素のingredientインスタンスを取得し、ingredientインスタンスのgetNameメソッドを実行する
                System.out.print(ingredients.get(i).getName());
                // 材料名ごとにカンマと空白を入れるが、最後にはカンマは不要
                    if (i < ingredients.size() - 1) { // 材料名ごとに、カンマと空白を入れる
                        System.out.print(",");
                    }
                }
            System.out.println();
            }
            System.out.println("-----------------------------------"); // 終わるときに----で閉じる
        }

        // 設問6 addNewRecipeメソッドの実装
        private void addNewRecipe() throws IOException {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String name;
            // 料理名の受け取り
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            name = reader.readLine();
            // 材料の受け取り
            System.out.println("Enter ingredients (type 'done' when finished):");
            boolean done = true;
            while (done) {
                System.out.print("Ingredient: ");
                String line = reader.readLine();
                if (line.equals("done")) {
                    done = false;
                } else {
                    Ingredient ing = new Ingredient(line);
                    ingredients.add(ing);
                }
            }
            Recipe recipe = new Recipe(name, ingredients);
            dataHandler.writeData(recipe);
        }
}

