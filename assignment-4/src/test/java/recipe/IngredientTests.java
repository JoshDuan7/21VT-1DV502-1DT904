package recipe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// These tests are to be uncommented for the passing grade
// preferably solve the tests in order
public class IngredientTests {

  @Test
  public void ingredientTestName() {
    Ingredient ingredient = new Ingredient();
    ingredient.setName("chicken");
    assertEquals("chicken", ingredient.getName());
  }

  @Test
  public void ingredientTestUnit() {
    Ingredient ingredient = new Ingredient();
    ingredient.setUnit("piece");
    assertEquals("piece", ingredient.getUnit());
  }

  @Test
  public void ingredientTestPrice() {
    Ingredient ingredient = new Ingredient();
    Double price = 12.99;
    ingredient.setPrice(price);
    assertEquals(price, ingredient.getPrice());
  }

  @Test
  public void ingredientTestDivisable() {
    Ingredient ingredient = new Ingredient();
    boolean isDivisable = false;
    ingredient.setDivisable(isDivisable);
    assertEquals(false, ingredient.isDivisable());
  }

}
