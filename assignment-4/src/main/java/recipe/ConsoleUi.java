package recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functionality to print the game state and player messages to the console,
 * also some console input helpers.
 */
public class ConsoleUi {
  Ingredient ingredient;
  RecipeApplication ra;
  String splitLine = "------------------------------";

  /**
   * Prompts for enter an ingredient's name.
   */
  public String promptForIngredientName() {
    System.out.println();
    System.out.println();
    String name;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      do {
        System.out.print("Please enter ingredient's name: ");
        name = reader.readLine();
      } while (name.equals(""));
      return name;
    } catch (IOException e) {
      e.printStackTrace();
      return "\0";
    }
  }

  /**
   * Prompts for enter an recipe's name.
   */
  public String promptForRecipetName() {
    System.out.println();
    System.out.println();
    String name;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      do {
        System.out.print("Please enter recipe's name: ");
        name = reader.readLine();
      } while (name.equals(""));
      return name;
    } catch (IOException e) {
      e.printStackTrace();
      return "\0";
    }
  }

  /** To identify if user's input is integer. */
  public boolean isIntNumeric(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /** To identify if double format number is an int number. */
  public boolean isDoubleInteger(double n) {
    int ni = (int) n;
    double nd = (double) ni;
    if (nd == n) {
      return true;
    }
    return false;
  }

  /** To identify if input is a number. */
  private boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /** Prompts for enter the portion number of the recipe. */
  public int promptForRecipePortionNum() {
    String portionStr;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      do {
        System.out.print("Please enter the positive integer 'NUMBER' for portion(s): ");
        portionStr = reader.readLine();
      } while (!isIntNumeric(portionStr) || portionStr.equals(""));
      return Integer.parseInt(portionStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  /** Prompts for enter portion unit of the recipe. */
  public String promptForRecipePortionUnit() {
    String portionUnit;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Please enter the 'UNIT' of portion(s): ");
      portionUnit = reader.readLine();
      if (portionUnit.equals("")) {
        System.out.println("The input cannot be empty! ");
        promptForRecipePortionUnit();
      }
      return portionUnit;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Prompts for enter comment of the recipe. */
  public String promptForRecipeComment() {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      System.out.print("Please enter the comment line by line (empty when done): ");
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Prompts for enter the name of the ingredient in the recipe. */
  public String promptForIngredientInRecipeName() {
    String ingredientInRecipeName;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));

      System.out.print("Please enter the ingredient 'Name' (empty when done): ");
      ingredientInRecipeName = reader.readLine();
      return ingredientInRecipeName;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Prompts for enter the amount of the ingredient in the recipe. */
  public double promptForIngredientInRecipeAmount() {
    String amount;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      do {
        System.out.print("Please enter the ingredient's 'Amount': ");
        amount = reader.readLine();
      } while (isNumeric(amount) == false || amount.equals(""));
      return Double.parseDouble(amount);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  /** UI for the ingredient does not exist. */
  public void ingredientNotExist() {
    System.out.println("This ingredient does not exist in the system, you need to create it based on the above name!");
  }

  /**
   * Prompts for all selections regarding recipes.
   */
  public Option promptForRecipeSelection() {
    System.out.println();
    System.out.println();
    System.out.println(" Which selection regarding recipes do you want to operate: ");
    System.out.println(" 1. Add a new recipe. ");
    System.out.println(" 2. List all available recipes. ");
    System.out.println(" 3. Look at the details of particular recipe. ");
    System.out.println(" 4. Delete a particular recipe. ");
    System.out.println(" 5. Search a recipe. ");
    System.out.println(" 6. Back to previous menu.");
    System.out.print(" Please make selection: ");
    String selection;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      selection = reader.readLine();
      if (selection.equals("1")) {
        return Option.CHECK_RECIPE_EXISTED;
      } else if (selection.equals("2")) {
        return Option.LIST_RECIPE;
      } else if (selection.equals("3")) {
        return Option.LOOK_RECIPE;
      } else if (selection.equals("4")) {
        return Option.DELETE_RECIPE;
      } else if (selection.equals("5")) {
        return Option.SEARCH_RECIPE;
      } else if (selection.equals("6")) {
        return Option.RETURN_START;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Prompts for all selections regarding ingredients.
   */
  public Option promptForIngredientSelection() {
    System.out.println();
    System.out.println();
    System.out.println(" Which selection regarding ingredients do you want to operate: ");
    System.out.println(" 1. Create and add an ingredient. ");
    System.out.println(" 2. List all available ingredients.");
    System.out.println(" 3. Look at the details of particular ingredient. ");
    System.out.println(" 4. Delete a particular ingredient. ");
    System.out.println(" 5. Back to previous menu. ");
    System.out.print(" Please make selection: ");
    String selection;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      selection = reader.readLine();
      if (selection.equals("1")) {
        return Option.CHECK_INGREDIENT_EXISTED;
      } else if (selection.equals("2")) {
        return Option.LIST_INGREDIENTS;
      } else if (selection.equals("3")) {
        return Option.SEARCH_INGREDIENT;
      } else if (selection.equals("4")) {
        return Option.DELETE_INGREDIENT;
      } else if (selection.equals("5")) {
        return Option.RETURN_START;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Prompts for start selections.
   */
  public Option promptForStartSelection() {
    System.out.println();
    System.out.println();
    System.out.println("####### WELCOME USE HEALTHY DAILY RECIPE! ########");
    System.out.println();
    System.out.println("Which selection do you want to operate: ");
    System.out.println("1. Ingredient operations. ");
    System.out.println("2. Recipe operations. ");
    System.out.println("0. Exit system and save updates. ");
    System.out.print("Please make selection: ");
    String selection;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      selection = reader.readLine();
      if (selection.equals("1")) {
        return Option.SELECT_INGREDIENT_MENU;
      } else if (selection.equals("2")) {
        return Option.SELECT_RECIPE_MENU;
      } else if (selection.equals("0")) {
        return Option.EXIT_SYSTEM;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** UI for exiting system. */
  public void exitSystem() {
    System.out.println("Exit system! ");
  }

  /** UI for duplicated ingredient name in the system. */
  public void ingredientDuplicated() {
    System.out.println();
    System.out.println("The ingredient has existed in the system cannot add again! ");
    System.out.println();
  }

  /** UI for duplicated recipe name in the system. */
  public void recipeDuplicated() {
    System.out.println();
    System.out.println("The recipe has existed in the system cannot add again! ");
    System.out.println();
  }

  /** UI for deleted ingredient. */
  public void ingredientDeleted(String name) {
    System.out.println();
    System.out.println("Ingredient " + name + "has been deleted from the system!");
  }

  /** Prompt for creating a new ingredient. */
  public Ingredient promptForCreateIngredient(String name) {
    ingredient = new Ingredient();
    String unit;
    String priceStr;
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      ingredient.setName(name);

      do {
        System.out.print("Please enter unit: ");
        unit = reader.readLine();
      } while (unit.equals(""));
      ingredient.setUnit(unit);

      if (ingredient.getUnit().toLowerCase().equals("piece")) {
        ingredient.setDivisable(false);
      } else {
        ingredient.setDivisable(true);
      }

      do {
        System.out.print("Please enter price: "); // need to valid if it is digit
        priceStr = reader.readLine();
      } while (isNumeric(priceStr) == false);
      Double price = Double.parseDouble(priceStr);

      ingredient.setPrice(price);
      System.out.println("Has successfully added " + "'" + ingredient.getName() + "'!");

    } catch (IOException e) {
      e.printStackTrace();
    }
    return ingredient;
  }

  /** UI for listing all the ingredients in the system. */
  public void listIngredients(List<Ingredient> ingredients) {
    System.out.println();
    System.out.println();
    System.out.println("Available ingredients");
    System.out.println(splitLine);
    int count = 0;
    for (Ingredient ingredient : ingredients) {
      count++;
      String name = ingredient.getName();
      String unit = ingredient.getUnit();
      Double price = ingredient.getPrice();
      System.out.println("[No." + count + "]");
      System.out.println("Ingredient name: " + name);
      System.out.println("Ingredient unit: " + unit);
      System.out.println("Ingredient price: " + price + " kr");
      System.out.println(splitLine);
    }
  }

  /** UI for showing the detail of the ingredient for look. */
  public void lookIngredient(List<Ingredient> ingredients, String nameForSearch) {
    System.out.println();
    Boolean flag = false;
    for (Ingredient ingredient : ingredients) {
      String name = ingredient.getName();
      String unit = ingredient.getUnit();
      Double price = ingredient.getPrice();
      if (nameForSearch.equals(name)) {
        System.out.println();
        System.out.println("The ingredient's detail: ");
        System.out.println(name + ":" + unit + ":" + price);
        System.out.println();
        flag = true;
        break;
      }
    }
    if (flag == false) {
      System.out.println();
      System.out.println("No such ingredient in the system! ");
      System.out.println();
    }
  }

  /** UI for deleting an ingredient from system. */
  public void deleteIngredient() {
    System.out.println();
    System.out.println("The ingredient has been successfully deleted from the system!");
    System.out.println();
  }

  /** UI for listing all recipes from the system. */
  public void listRecipes(List<Recipe> recipes) {
    RecipeMenuBehaviours recipeMenuBehaviours = new RecipeMenuBehaviours();

    System.out.println();
    System.out.println("Availble Recipes");
    System.out.println(splitLine);
    int count = 1;
    for (Recipe recipe : recipes) {
      System.out.println("[Recipe No." + count + "]");
      count++;
      String recipeName = recipe.getName();
      int recipePortion = recipe.getPortion();
      String portionUnit = recipe.getPortionUnit();
      List<IngredientInRecipe> ingredientsInRecipe = recipe.getIngredientsInRecipe();
      List<String> comments = recipe.getComments();

      System.out.println(recipeName);
      System.out.print(recipePortion);
      System.out.print(" ");
      System.out.println(portionUnit);
      System.out.println();
      System.out.println("Ingredients: ");
      for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {
        Double ingredientAmount = ingredientInRecipe.getAmount();
        String unit = ingredientInRecipe.getIngredient().getUnit();
        String ingredientName = ingredientInRecipe.getIngredient().getName();
        System.out.println("" + ingredientAmount + " " + unit + " " + ingredientName);
      }
      System.out.println();
      System.out.println("Cooking comments: ");
      for (String comment : comments) {
        System.out.println(comment);
      }
      System.out.println(splitLine);
      System.out.println("Recipe price: " + "(" + recipeMenuBehaviours.calculatePrice(recipe) + " kr" + ")");
      System.out.println();
    }
  }

  /** UI for showing the focal recipe. */
  public void showRecipe(Recipe recipe) {
    RecipeMenuBehaviours recipeMenuBehaviours = new RecipeMenuBehaviours();
    System.out.println(splitLine);
    String recipeName = recipe.getName();
    int recipePortion = recipe.getPortion();
    String portionUnit = recipe.getPortionUnit();
    List<IngredientInRecipe> ingredientsInRecipe = recipe.getIngredientsInRecipe();
    List<String> comments = recipe.getComments();

    System.out.println(recipeName);
    System.out.print(recipePortion);
    System.out.print(" ");
    System.out.println(portionUnit);
    System.out.println();
    System.out.println("Ingredients: ");
    for (IngredientInRecipe ingredientInRecipe : ingredientsInRecipe) {
      Double ingredientAmount = ingredientInRecipe.getAmount();
      String unit = ingredientInRecipe.getIngredient().getUnit();
      String ingredientName = ingredientInRecipe.getIngredient().getName();
      System.out.println("" + ingredientAmount + " " + unit + " " + ingredientName);
    }
    System.out.println();
    System.out.println("Cooking comments: ");
    for (String comment : comments) {
      System.out.println(comment);
    }
    System.out.println(splitLine);
    System.out.println("Recipe price: " + "(" + recipeMenuBehaviours.calculatePrice(recipe) + " kr" + ")");
    System.out.println();
  }

  /** UI for deleting a recipe from system. */
  public void deleteRecipe() {
    System.out.println();
    System.out.println("This recipe has been successfully removed from the system! ");
  }

  /** Prompt for enter a recipe name for look. */
  public String findRecipeForLook() {
    System.out.println();
    System.out.print("Enter the recipe's mame for look: ");
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** UI for notifying the original recipe. */
  public void originalRecipe() {
    System.out.println();
    System.out.println("The original recipe portion and ingredients' amount are as follow: ");
  }

  /** Prompt for enter customized recipe portion. */
  public int promptForPortionRequest() {
    System.out.println();
    System.out.println();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      String portionStr;
      do {
        System.out.print("How many portion(s) do you want (Please input a positive integer number): ");
        portionStr = reader.readLine();
      } while (isIntNumeric(portionStr) == false);
      return Integer.parseInt(portionStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  /** Prompt for enter recipe search methods. */
  public Option promptForRecipeSearchMethodSelection() {

    String selection;

    System.out.println();
    System.out.println();
    System.out.println("1. Search by recipe's ingredient name. ");
    System.out.println("2. Search recipes by max price. ");
    System.out.println("3. Search by recipe's name. ");
    System.out.println("4. Back to previous menu. ");
    System.out.print("Please make selection: ");
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      selection = reader.readLine();
      if (selection.equals("1")) {
        return Option.SEARCH_RECIPE_BY_INGREDIENT_NAME;
      } else if (selection.equals("2")) {
        return Option.SEARCH_RECIPE_BY_MAX_PRICE;
      } else if (selection.equals("3")) {
        return Option.SEARCH_RECIPE_BY_RECIPE_NAME;
      } else if (selection.equals("4")) {
        return Option.RETURN_RECIPE_MENU;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Prompt for enter the name for the ingredient in the recipe. */
  public String promptForIngredientInRecipe() {

    System.out.print("Please enter the ingredient 'Name' for search: ");
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      String ingredientName = reader.readLine();
      return ingredientName;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Prompt for enter the wanted max price. */
  public String promptForWantedMaxPrice() {

    System.out.print("Please enter the required max price: ");
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      String maxPriceStr = reader.readLine();
      if (isNumeric(maxPriceStr) == false) {
        System.out.println("Please input valid number! ");
        promptForWantedMaxPrice();
      }
      return maxPriceStr;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** Notify for indivisable ingredient. */
  public void ingredientIndivisable() {
    System.out.println("This ingredient is indivisable, please input positive integer! ");
  }

  /** Notify for positive integer input. */
  public void notifyPositiveInteger() {
    System.out.println("Please input valid positive integer number! ");
  }

  /** Notify for invalid input for menu. */
  public void notifyValidInputForMenu() {
    System.out.println();
    System.out.println();
    System.out.println("Please input valid selection from menu! ");
  }

  public void noRecipeMeetSearchRequirement() {
    System.out.println();
    System.out.println();
    System.out.println("There is no recipe fulfilling search requirement! ");
  }

}
