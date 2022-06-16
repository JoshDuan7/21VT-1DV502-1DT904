package recipe;

/** This class is for ingredientInrecipe objects. */
public class IngredientInRecipe {
  private Double amount;
  private Ingredient ingredient;

  public IngredientInRecipe() {

  }

  public IngredientInRecipe(IngredientInRecipe ingredientInRecipe) {
    this.amount = ingredientInRecipe.amount;
    this.ingredient = ingredientInRecipe.ingredient;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

}
