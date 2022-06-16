package recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByRecipeName implements Search {
  String recipeName;
  List<Recipe> targetRecipes = new ArrayList<>();
  List<IngredientInRecipe> ingredientsInRecipe;

  /** Search for recipes with same name or the recipe's name includes the name for search. */
  public List<Recipe> search(List<Recipe> recipes, String recipeName) {
    for (Recipe recipe : recipes) {
      if (recipe.getName().toLowerCase().contains(recipeName.toLowerCase())) {
        targetRecipes.add(recipe);
      }
    }
    return targetRecipes;
  }
}
