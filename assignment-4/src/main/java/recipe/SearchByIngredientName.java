package recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByIngredientName implements Search {
  List<IngredientInRecipe> ingredientsInRecipe;
  List<Recipe> targetRecipes = new ArrayList<>();

  /** Search for recipes include the ingredient. */
  public List<Recipe> search(List<Recipe> recipes, String ingredientName) {
    for (Recipe recipe : recipes) {
      ingredientsInRecipe = recipe.getIngredientsInRecipe();
      for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {
        if (ingredientInRecipe.getIngredient().getName().toLowerCase().contains(ingredientName.toLowerCase())) {
          targetRecipes.add(recipe);
        }
      }
    }
    return targetRecipes;
  }
}
