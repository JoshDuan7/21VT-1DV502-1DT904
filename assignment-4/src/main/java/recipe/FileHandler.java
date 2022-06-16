package recipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

  /** Write all ingredient objects to the 'ingredients.txt' file. */
  public void writeIngredients(List<Ingredient> ingredients) {
    try {
      File outFile = new File("files/ingredients.txt");
      PrintWriter printer = new PrintWriter(outFile);
      for (Ingredient ingredient : ingredients) {
        String name = ingredient.getName();
        String unit = ingredient.getUnit();
        String price = ingredient.getPrice().toString();
        String finalText = "" + name + ";" + unit + ";" + price;
        printer.print(finalText);
        printer.print("\n");
      }
      printer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Read all ingredient objects from 'ingredients.txt' file to system. */
  public List<Ingredient> readIngredients() {
    List<Ingredient> result = new ArrayList<>();
    try {
      File file = new File("files/ingredients.txt");
      FileReader fr = new FileReader(file);
      try (BufferedReader br = new BufferedReader(fr)) {
        String line;
        while ((line = br.readLine()) != null) {
          String[] ingreStr = line.split(";");
          Ingredient ingredient = new Ingredient();
          ingredient.setName(ingreStr[0]);
          ingredient.setUnit(ingreStr[1]);
          ingredient.setPrice(Double.parseDouble(ingreStr[2]));
          // If the ingredient's unit equals 'piece' then it is nondivisable
          if (ingreStr[1].equals("piece")) {
            ingredient.setDivisable(false);
          } else {
            ingredient.setDivisable(true);
          }
          result.add(ingredient);
        }
      } finally {
        fr.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /** Write all recipe objects to the 'recipe.txt' file. */
  public void writeRecipes(List<Recipe> recipes) {
    try {
      File outFile = new File("files/recipes.txt");
      PrintWriter printer = new PrintWriter(outFile);
      for (Recipe recipe : recipes) {
        String recipeName = recipe.getName();
        int recipePortion = recipe.getPortion();
        String recipePortionUnit = recipe.getPortionUnit();
        List<IngredientInRecipe> ingredientsInRecipe = recipe.getIngredientsInRecipe();
        List<String> comments = recipe.getComments();
        String ingredientsInRecipeText = "";
        for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {
          double amount = ingredientInRecipe.getAmount();
          String ingredientUnit = ingredientInRecipe.getIngredient().getUnit();
          String ingredientName = ingredientInRecipe.getIngredient().getName();
          ingredientsInRecipeText += amount + "," + ingredientUnit + "," + ingredientName + "-";
        }
        ingredientsInRecipeText = ingredientsInRecipeText.substring(0, ingredientsInRecipeText.length() - 1);
        String commentsText = "";
        for (String comment : comments) {
          if (comment.substring(0, 1).equals("*")) {
            commentsText += comment.substring(0, 1).toUpperCase() + comment.substring(1) + ",";
          } else {
            commentsText += "*" + comment.substring(0, 1).toUpperCase() + comment.substring(1) + ",";
          }
        }
        commentsText = commentsText.substring(0, commentsText.length() - 1);

        String finalText = recipeName + ";" + recipePortion + "," + recipePortionUnit + ";" + ingredientsInRecipeText
            + ";" + commentsText;
        printer.print(finalText);
        printer.print("\n");
      }
      printer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Read all recipe objects from 'recipes.txt' file to system. */
  public List<Recipe> readRecipes(List<Ingredient> ingredients) {
    List<Recipe> result = new ArrayList<>();
    try {
      File file = new File("files/recipes.txt");
      FileReader fr = new FileReader(file);
      try (BufferedReader br = new BufferedReader(fr)) {
        String line;
        while ((line = br.readLine()) != null) {
          // read all content from recipes.txt
          String[] reciStr = line.split(";");

          // new recipe object
          Recipe recipe = new Recipe();
          recipe.setName(reciStr[0]);

          String[] commentsStr = reciStr[3].split(",");
          List<String> comments = new ArrayList<>();

          // save comments
          for (String string : commentsStr) {
            comments.add(string);
          }
          recipe.setComments(comments);

          // read the recipe's portion and portion unit from recipes.txt
          String[] recipePortion = reciStr[1].split(",");
          recipe.setPortion(Integer.parseInt(recipePortion[0]));
          recipe.setPortionUnit(recipePortion[1]);

          // read all ingredients of current recipe
          String[] ingredientsInTheFile = reciStr[2].split("-");
          List<IngredientInRecipe> ingredientsInRecipe = new ArrayList<>();
          for (String str1 : ingredientsInTheFile) {
            String[] eachIngredient = str1.split(",");
            for (Ingredient ingredient : ingredients) {
              if (ingredient.getName().equals(eachIngredient[2])) {
                IngredientInRecipe ingredientInRecipe = new IngredientInRecipe();
                ingredientInRecipe.setAmount(Double.parseDouble(eachIngredient[0]));
                ingredientInRecipe.setIngredient(ingredient);
                ingredientsInRecipe.add(ingredientInRecipe);
              }
            }
          }
          recipe.setIngredientsInRecipe(ingredientsInRecipe);
          result.add(recipe);
        }
      } finally {
        fr.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
