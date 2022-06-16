package recipe;

import java.util.ArrayList;
import java.util.List;

/** This class is for recipe objects. */
public class Recipe {
  private String name;
  private int portion;
  private String portionUnit;
  private List<IngredientInRecipe> ingredientsInRecipe;
  private List<String> comments;

  public Recipe() {

  }

  public Recipe(Recipe recipe) {
    this.name = recipe.name;
    this.portion = recipe.portion;
    this.portionUnit = recipe.portionUnit;
    ingredientsInRecipe = new ArrayList<>();
    for (IngredientInRecipe ingre : recipe.getIngredientsInRecipe()) {
      ingredientsInRecipe.add(new IngredientInRecipe(ingre));
    }
    this.comments = recipe.comments;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPortion() {
    return portion;
  }

  public void setPortion(int portion) {
    this.portion = portion;
  }

  public String getPortionUnit() {
    return portionUnit;
  }

  public void setPortionUnit(String portionUnit) {
    this.portionUnit = portionUnit;
  }

  public List<IngredientInRecipe> getIngredientsInRecipe() {
    return ingredientsInRecipe;
  }

  public void setIngredientsInRecipe(List<IngredientInRecipe> ingredientsInRecipe) {
    this.ingredientsInRecipe = ingredientsInRecipe;
  }

  public List<String> getComments() {
    return comments;
  }

  public void setComments(List<String> comments) {
    this.comments = comments;
  }

}
