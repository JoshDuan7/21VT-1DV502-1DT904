package recipe;

import java.util.List;

public class IngredientMenuBehaviours {
  private ConsoleUi ui = new ConsoleUi();

  /**
   * This method is used for identify if the ingredient name already exists in
   * ingredient list.
   */
  public boolean isIngredientExist(List<Ingredient> ingredients, String ingredientName) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().toLowerCase().equals(ingredientName.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * First to check if the ingredient is in the system. If the ingredient is
   * duplicated by same name, the system will give notification, if the name is
   * unique, the system will allow the user to create.
   */
  public void checkIngredient(List<Ingredient> ingredients) {
    boolean isDuplicated;
    String ingredientName;

    ingredientName = ui.promptForIngredientName();
    isDuplicated = isIngredientExist(ingredients, ingredientName);

    if (isDuplicated == true) {
      ui.ingredientDuplicated();
    } else {
      ingredients.add(createIngredient(ingredientName));
    }
  }

  /**
   * This method will be automatically called by checkIngredient() if the
   * ingredient name is unique from user-friendly perspective.
   */
  public Ingredient createIngredient(String name) {
    Ingredient ingredient = ui.promptForCreateIngredient(name);
    return ingredient;
  }

  /** This method will list all ingredients from the system's file. */
  public void listIngredients(List<Ingredient> ingredients) {
    ui.listIngredients(ingredients);
  }

  /** This method is for looking ingredient's detail. */
  public void lookIngredient(List<Ingredient> ingredients, String nameForSearch) {
    ui.lookIngredient(ingredients, nameForSearch);
  }

  /** This method is used to get the ingredient object by name. */
  public Ingredient getIngredientByName(List<Ingredient> ingredients, String name) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(name)) {
        return ingredient;
      }
    }
    return null;
  }

  /**
   * This method is for deleting ingredient if it exists. It will also update
   * recipe's ingredient content if the ingredient in the recipe.
   */
  public void deleteIngredient(List<Ingredient> ingredients, List<Recipe> recipes, String nameForSearch) {
    for (int i = ingredients.size() - 1; i >= 0; i--) {
      if (ingredients.get(i).getName().equals(nameForSearch)) {
        ingredients.remove(ingredients.get(i));
        ui.deleteIngredient();
      }
    }
    // When the above ingredient is deleted from the system, the ingredientInRecipe
    // with same name should also
    // be deleted due to synchronization
    for (Recipe recipe : recipes) {
      for (int i = recipe.getIngredientsInRecipe().size() - 1; i >= 0; i--) {
        if (recipe.getIngredientsInRecipe().get(i).getIngredient().getName().equals(nameForSearch)) {
          recipe.getIngredientsInRecipe().remove(recipe.getIngredientsInRecipe().get(i));
        }
      }
    }
  }

}
