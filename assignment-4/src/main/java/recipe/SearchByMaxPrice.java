package recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByMaxPrice implements Search {
  int portion;
  double maxPrice;
  double recipePrice;
  List<IngredientInRecipe> ingredientsInRecipe;
  RecipeMenuBehaviours recipeMenuBehaviours = new RecipeMenuBehaviours();
  List<Recipe> targetRecipes = new ArrayList<>();
  ConsoleUi ui = new ConsoleUi();

  /**Search for recipes' max price less or equal than the required max price. */
  public List<Recipe> search(List<Recipe> recipes, String maxPriceStr) {
    maxPrice = Double.parseDouble(maxPriceStr);
    for (Recipe recipe : recipes) {
      recipePrice = recipeMenuBehaviours.calculatePrice(recipe);
      if (recipePrice <= maxPrice) {
        targetRecipes.add(recipe);
      }
    }
    return targetRecipes;
  }
}
