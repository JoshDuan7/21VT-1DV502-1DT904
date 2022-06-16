package recipe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecipeMenuBehaviours {
  private ConsoleUi ui = new ConsoleUi();

  /** This method is used to identify if the input recipe name already existed. */
  public boolean isRecipeExist(List<Recipe> recipes, String recipeName) {
    for (Recipe recipe : recipes) {
      if (recipe.getName().toLowerCase().equals(recipeName.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method is firstly used to check if the input recipe's name already
   * existed in the system.
   */
  public void checkRecipe(List<Recipe> recipes, List<Ingredient> ingredients) {
    boolean isDuplicated = false;
    String recipeName;

    recipeName = ui.promptForRecipetName();
    isDuplicated = isRecipeExist(recipes, recipeName);

    if (isDuplicated == true) {
      ui.recipeDuplicated();
    } else {
      recipes.add(createRecipe(recipeName, ingredients));
    }
  }

  /**
   * This method is used to create a new recipe if the recipe name is unque in the
   * system.
   */
  public Recipe createRecipe(String recipeName, List<Ingredient> ingredients) {
    String ingredientName;
    boolean isIngredientExist;
    IngredientMenuBehaviours ingredientMenuBehaviours = new IngredientMenuBehaviours();
    List<IngredientInRecipe> ingredientsInRecipe = new ArrayList<>();
    List<String> comments = new ArrayList<>();
    Recipe recipe = new Recipe();
    Ingredient ingredient;
    double amount;

    recipe.setName(recipeName);
    recipe.setPortion(ui.promptForRecipePortionNum());
    recipe.setPortionUnit(ui.promptForRecipePortionUnit());

    int count = 0;
    // Continue to add ingredient until empty input
    do {
      count++;
      ingredientName = ui.promptForIngredientInRecipeName().toLowerCase();
      while (count == 1 && ingredientName.equals("")) {
        ingredientName = ui.promptForIngredientInRecipeName().toLowerCase();
      }
      if (ingredientName != null && ingredientName.length() > 0) {
        isIngredientExist = ingredientMenuBehaviours.isIngredientExist(ingredients, ingredientName);
        // If the ingredient does not exist in the system
        if (isIngredientExist == false) {
          ui.ingredientNotExist();

          // The user need to first to create it
          ingredient = ui.promptForCreateIngredient(ingredientName);
          ingredients.add(ingredient);

          // The new ingredient object will be added to an ingredientInRecipe object
          IngredientInRecipe theNewIngredientInRecipe = new IngredientInRecipe();
          amount = ui.promptForIngredientInRecipeAmount();
          
          // If the ingredient is indivisable
          if (ingredient.isDivisable() == false && ui.isDoubleInteger(amount) == false) {
            ui.ingredientIndivisable();
            amount = ui.promptForIngredientInRecipeAmount();
          }
          theNewIngredientInRecipe.setAmount(amount);
          theNewIngredientInRecipe.setIngredient(ingredient);

          // Add the new ingredientInRecipe object to ingredientInRecipes List
          ingredientsInRecipe.add(theNewIngredientInRecipe);
          recipe.setIngredientsInRecipe(ingredientsInRecipe);
        } else {
          // If the ingredient exists in the system, the ingredientInRecipe will be
          // directly created
          IngredientInRecipe ingredientInRecipe = new IngredientInRecipe();

          ingredient = ingredientMenuBehaviours.getIngredientByName(ingredients, ingredientName);
          ingredientInRecipe.setIngredient(ingredient);
          amount = ui.promptForIngredientInRecipeAmount();

          if (ingredient.isDivisable() == false) {
            while (ui.isDoubleInteger(amount) == false) {
              ui.ingredientIndivisable();
              amount = ui.promptForIngredientInRecipeAmount();
            }
          }
          ingredientInRecipe.setAmount(amount);

          // Add the new ingredient in List<IngredientInRecipe> ingredientsInRecipe
          ingredientsInRecipe.add(ingredientInRecipe);
          recipe.setIngredientsInRecipe(ingredientsInRecipe);
        }
      } else if (recipe.getIngredientsInRecipe().size() > 0) {
        // At least one ingredient in the recipe
        break;
      }
    } while (true);

    // Continue to add comment until empty input
    do {
      String comment = ui.promptForRecipeComment();
      if (comment != null && comment.length() > 0) {
        if (comment.substring(0, 1).equals("*")) {
          comment = comment.substring(0, 1).toUpperCase() + comment.substring(1);
          comments.add(comment);
        } else {
          comment = "*" + comment.substring(0, 1).toUpperCase() + comment.substring(1);
          comments.add(comment);
        }
      } else if (comments.size() > 0) {
        break;
      }
    } while (true);

    recipe.setComments(comments);
    return recipe;

  }

  /** This method is used to list all recipes in the system */
  public void listRecipes(List<Recipe> recipes) {
    ui.listRecipes(recipes);
  }

  public void lookRecipe(List<Recipe> recipes) {

    String name = ui.findRecipeForLook().toLowerCase();

    for (Recipe recipe : recipes) {
      if (recipe.getName().toLowerCase().equals(name)) {
        ui.originalRecipe();
        ui.showRecipe(recipe);

        Recipe customizedRecipe = new Recipe(recipe);
        int portionRequest = ui.promptForPortionRequest();

        // Calculate ingredientsInRecipe with customized amount
        List<IngredientInRecipe> customizedIngredientsInRecipe = calculateCustomizedIngredientsInRecipe(
            customizedRecipe, portionRequest);
        customizedRecipe.setIngredientsInRecipe(customizedIngredientsInRecipe);
        customizedRecipe.setPortion(portionRequest);

        // show the new customized recipe for user
        ui.showRecipe(customizedRecipe);
      }
    }
  }

  /** This method is used to calculate total price of the customized recipe */
  public List<IngredientInRecipe> calculateCustomizedIngredientsInRecipe(Recipe recipe, int portionRequest) {

    DecimalFormat df = new DecimalFormat("0.00");
    List<IngredientInRecipe> ingredientsInRecipe = recipe.getIngredientsInRecipe();
    for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {

      // ingredient's attribute isDivisable equals true, the ingredient can be divided
      if (ingredientInRecipe.getIngredient().isDivisable() == true) {
        double amount = Double
            .parseDouble(df.format((double) ingredientInRecipe.getAmount() / recipe.getPortion() * portionRequest));
        ingredientInRecipe.setAmount(amount);
        // isDivisable equals false, the ingredient can not be divided, the system will
        // round up the amound to integer
      } else {
        double amount = Math.ceil(Double
            .parseDouble(df.format((double) ingredientInRecipe.getAmount() / recipe.getPortion() * portionRequest)));
        ingredientInRecipe.setAmount(amount);
      }
    }
    return ingredientsInRecipe;
  }

  /** This method is used to calculate total price of the recipe */
  public double calculatePrice(Recipe recipe) {
    double ingredientsPrice = 0;
    DecimalFormat df = new DecimalFormat("0.00");

    List<IngredientInRecipe> ingredientsInRecipe = recipe.getIngredientsInRecipe();
    for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {
      ingredientsPrice += ingredientInRecipe.getAmount() * ingredientInRecipe.getIngredient().getPrice();
    }
    return Double.parseDouble(df.format(ingredientsPrice));
  }

  /** This method is used for deleting the typical recipe by name */
  public void deleteRecipe(List<Recipe> recipes, String nameForSearch) {
    for (Recipe recipe : recipes) {
      if (recipe.getName().toLowerCase().equals(nameForSearch.toLowerCase())) {
        recipes.remove(recipe);
        ui.deleteRecipe();
        break;
      }
    }
  }

  /** This method is used for searching availble recipes according to ingredient name */
  public void searchRecipeByIngredientName(List<Recipe> recipes) {
    Search searchEngine;
    String searchTarget;
    List<Recipe> targetRecipes;
    searchEngine = new SearchByIngredientName();
    searchTarget = ui.promptForIngredientInRecipe();
    targetRecipes = searchEngine.search(recipes, searchTarget);
    if(targetRecipes.isEmpty()) {
      ui.noRecipeMeetSearchRequirement();
    } else {
      ui.listRecipes(targetRecipes);
    }
  }

  /** This method is used for searching availble recipes according to max price of the recipe */
  public void searchRecipeByMaxPrice(List<Recipe> recipes) {
    Search searchEngine;
    String searchTarget;
    List<Recipe> targetRecipes;
    searchEngine = new SearchByMaxPrice();
    searchTarget = ui.promptForWantedMaxPrice();
    targetRecipes = searchEngine.search(recipes, searchTarget);
    if(targetRecipes.isEmpty()) {
      ui.noRecipeMeetSearchRequirement();
    } else {
      ui.listRecipes(targetRecipes);
    }
  }

  /** This method is used for searching availble recipes according to recipe name */
  public void searchRecipeByRecipeName(List<Recipe> recipes) {
    Search searchEngine;
    String searchTarget;
    List<Recipe> targetRecipes;
    searchEngine = new SearchByRecipeName();
    searchTarget = ui.promptForRecipetName();
    targetRecipes = searchEngine.search(recipes, searchTarget);
    if(targetRecipes.isEmpty()) {
      ui.noRecipeMeetSearchRequirement();
    } else {
      ui.listRecipes(targetRecipes);
    }
  }

}
