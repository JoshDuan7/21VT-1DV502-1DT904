package recipe;

import java.util.List;

public class RecipeApplication {
  private ConsoleUi ui;
  private FileHandler fileHandler;
  private IngredientMenuBehaviours ingredientMenuBehaviours;
  private RecipeMenuBehaviours recipeMenuBehaviours;
  private List<Ingredient> ingredients;
  private List<Recipe> recipes;

  RecipeApplication() {
    fileHandler = new FileHandler();
    ui = new ConsoleUi();
    ingredientMenuBehaviours = new IngredientMenuBehaviours();
    recipeMenuBehaviours = new RecipeMenuBehaviours();
    ingredients = fileHandler.readIngredients();
    recipes = fileHandler.readRecipes(ingredients);
  }

  /** This method is for functions of ingredient menu. */
  public void ingredientMenu() {
    Option select = ui.promptForIngredientSelection();

      // 1. Check if the entered ingredient existed in the system, if not then will
      // automatically start to create
    if (select == Option.CHECK_INGREDIENT_EXISTED) {
      ingredientMenuBehaviours.checkIngredient(ingredients);
      ingredientMenu();

      // 2. List all existed ingredients
    } else if (select == Option.LIST_INGREDIENTS) {
      ingredientMenuBehaviours.listIngredients(ingredients);
      ingredientMenu();

      // 3. Search the ingredient and look its details
    } else if (select == Option.SEARCH_INGREDIENT) {
      String nameForSearch = ui.promptForIngredientName();
      ingredientMenuBehaviours.lookIngredient(ingredients, nameForSearch);
      ingredientMenu();

      // 4. Delete the ingredient by name
    } else if (select == Option.DELETE_INGREDIENT) {
      String nameForSearch = ui.promptForIngredientName();
      ingredientMenuBehaviours.deleteIngredient(ingredients, recipes, nameForSearch);
      ingredientMenu();

      // 5. Return to start
    } else if (select == Option.RETURN_START) {
      start();
      
      // Notify user valid selection for menu
    } else {
      ui.notifyValidInputForMenu();
      ingredientMenu();
    }
  }

  /** This method is for different functions of recipe menu. */
  public void recipeMenu() {
    Option select = ui.promptForRecipeSelection();

      // 1. Check if the entered recipe existed in the system, if not then will
      // automatically start to create it
    if (select == Option.CHECK_RECIPE_EXISTED) {
      recipeMenuBehaviours.checkRecipe(recipes, ingredients);
      recipeMenu();

      // 2. List all existed recipes
    } else if (select == Option.LIST_RECIPE) {
      recipeMenuBehaviours.listRecipes(recipes);
      recipeMenu();

      // 3. Look the recipe, modify the portion numbers and provide corresponding
      // amounts of ingredients
    } else if (select == Option.LOOK_RECIPE) {
      recipeMenuBehaviours.lookRecipe(recipes);
      recipeMenu();

      // 4. Delete the recipe by name
    } else if (select == Option.DELETE_RECIPE) {
      String nameForSearch = ui.promptForRecipetName();
      recipeMenuBehaviours.deleteRecipe(recipes, nameForSearch);
      recipeMenu();

      // 5. Search the recipe
    } else if (select == Option.SEARCH_RECIPE) {
      recipeSearchMenu();
      recipeMenu();

      // 6. Return to start
    } else if (select == Option.RETURN_START) {
      start();
    
      // Notify user valid selection for menu
    } else {
      ui.notifyValidInputForMenu();
      recipeMenu();
    }
  }

  /** This method is for search functions of recipe menu. */
  public void recipeSearchMenu() {
    Option select = ui.promptForRecipeSearchMethodSelection();

      // 1. Search recipes by ingredient's name
    if (select == Option.SEARCH_RECIPE_BY_INGREDIENT_NAME) {
      recipeMenuBehaviours.searchRecipeByIngredientName(recipes);
      recipeSearchMenu();

      // 2. Search recipes by max price
    } else if (select == Option.SEARCH_RECIPE_BY_MAX_PRICE) {
      recipeMenuBehaviours.searchRecipeByMaxPrice(recipes);
      recipeSearchMenu();

      // 3. Search recipes by recipe's name
    } else if (select == Option.SEARCH_RECIPE_BY_RECIPE_NAME) {
      recipeMenuBehaviours.searchRecipeByRecipeName(recipes);
      recipeSearchMenu();

      // 4. Return to previous menu
    } else if (select == Option.RETURN_RECIPE_MENU) {
      recipeMenu();

       // Notify user valid selection for menu
    } else {
      ui.notifyValidInputForMenu();
      recipeSearchMenu();
    }
  }

  /** This method is for start menu. */
  public void start() {

    Option select = ui.promptForStartSelection();

      // 1. Ingredient menu
    if (select == Option.SELECT_INGREDIENT_MENU) {
      ingredientMenu();
      // 2. Recipe menu
    } else if (select == Option.SELECT_RECIPE_MENU) {
      recipeMenu();
      // 0. Exit system
    } else if (select == Option.EXIT_SYSTEM) {
      ui.exitSystem();
      fileHandler.writeIngredients(ingredients);
      fileHandler.writeRecipes(recipes);
      System.exit(0);
      // Notify user valid selection for menu
    } else {
      ui.notifyValidInputForMenu();
      start();
    }
  }

}
