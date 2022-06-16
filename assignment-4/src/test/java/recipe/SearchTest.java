package recipe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SearchTest {
  FileHandler fh = new FileHandler();
  List<Ingredient> ingredientsForTest = fh.readIngredients();
  List<Recipe> recipesForTest = fh.readRecipes(ingredientsForTest);
  
  @Test
  public void searchByRecipeNameTest() {
    List<Recipe> searchResults = new ArrayList<>();
    String searchTarget = "Egg pie";
    Search searchEngine = new SearchByRecipeName();
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    for (Recipe recipe : searchResults) {
      assertEquals("Egg pie", recipe.getName());
    }
    assertEquals(1, searchResults.size());
  }

  @Test
  public void searchByRecipeNameTest2() {
    List<Recipe> searchResults = new ArrayList<>();
    String searchTarget = "egg";
    Search searchEngine = new SearchByRecipeName();
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    assertEquals(2, searchResults.size());
  }

  @Test
  public void searchByIngredientName() {
    List<Recipe> searchResults = new ArrayList<>();
    String[] recipeNames = {"Gingerbread biscuit", "Egg pie", "Mushroom pizza", "Chicken burger"};
    String searchTarget = "flour";
    Search searchEngine = new SearchByIngredientName();
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    assertEquals(4, searchResults.size());
    int count = 0;
    for (String name : recipeNames) {
      for (Recipe recipe : searchResults) {
        if (name.equals(recipe.getName())){
          count++;
        }
      }
    }
    assertEquals(4, count);
    assertEquals(count, searchResults.size());
  }

  @Test
  public void searchByIngredientName2() {
    List<Recipe> searchResults = new ArrayList<>();
    String[] recipeNames = {"Lemon fish", "Steam fish"};
    String searchTarget = "lax";
    Search searchEngine = new SearchByIngredientName();
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    assertEquals(2, searchResults.size());
    int count = 0;
    for (String name : recipeNames) {
      for (Recipe recipe : searchResults) {
        if (name.equals(recipe.getName())){
          count++;
        }
      }
    }
    assertEquals(2, count);
    assertEquals(count, searchResults.size());
  }

  @Test
  public void SearchByMaxPrice() {
    List<Recipe> searchResults;
    Search searchEngine = new SearchByMaxPrice();
    
    String[] recipeNames = {"Chicken burger", "Banana egg milk"};
    String searchTarget = "30";
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    assertEquals(2, searchResults.size());
    int count = 0;
    for (String name : recipeNames) {
      for (Recipe recipe : searchResults) {
        if (name.equals(recipe.getName())){
          count++;
        }
      }
    }
    assertEquals(2, count);
    assertEquals(count, searchResults.size());

  }
  
  @Test
  public void SearchByMaxPrice2() {
    List<Recipe> searchResults;
    Search searchEngine = new SearchByMaxPrice();
    
    String[] recipeNames = {"Chicken burger", "Banana egg milk", "Steam fish", "Healthy noodles", "Gingerbread biscuit"};
    String searchTarget = "50";
    searchResults = searchEngine.search(recipesForTest, searchTarget);
    assertEquals(5, searchResults.size());
    int count = 0;
    for (String name : recipeNames) {
      for (Recipe recipe : searchResults) {
        if (name.equals(recipe.getName())){
          count++;
        }
      }
    }
    assertEquals(5, count);
    assertEquals(count, searchResults.size());
  }



}
