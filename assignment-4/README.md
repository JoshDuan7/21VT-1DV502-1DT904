# Assignment 4

## Introduction
This recipe application realized all the passing grade requirements designing with user-friendly perspectives. There are 14 classes in this design: ``ConsoleUi.java`` is used for presenting all the console-based user interfaces which encapsulated all the printing methods. ``Option.java`` is an enum class is used for interacting between user input and menu selections aiming for avoiding hidden dependency and id-connections. ``FileHandler.java`` is developed for reading "ingredients.txt" and "recipe.txt" in the initial stage of this program and write all the updated contents to the files when user exits the system. ``Recipe.java``, ``Ingredient.java``, and ``IngredientInRecipe`` are three classes used for creating related objects from the .txt files. ``IngredientInRecipe`` is used for creating the ingredient objects for recipe objects including two attributes: the ``"amount"`` of the ingredient and the ingredient object itself. ``IngredientMenuBehaviours.java`` achieves "create and add", "list", "look", and "delete" functions. ``RecipeMenuBehaviours.java`` realizes "add", "list", "look", "delete" and "search" functions. Search available recipe function is implemented based on ``strategy pattern`` – a ``interface`` and three ``interface implementation classes`` that user can search for recipe based on ``"ingredient name"``, ``"max price of recipe"``, ``"recipe name"`` (either same name or the recipe include the search name). ``Main.java`` includes the main method where is the entrance to start this program.

## Ingredient Menu Behaviours
1. Create and add an ingredient – to create and add a new ingredient to the system, the method ``isIngredientExist()`` and ``checkIngredient()`` are used for checking if the input new ingredient has existed in the system. If the ingredient name is not duplicated, the user can directly start to create it and then input ``"ingredient unit"`` and ``"price"`` otherwise the system's UI will give notification that the ingredient already existed. The create process is based on step by step introductions. In this design, the unit ``"piece"`` of ingrdient is considered as indivisible therefore if user input ``"piece"`` as the unit, the ingredient object will automatically set ``"False"`` to ``"isDivisible"`` attribute.

2. List all available ingredients – this method will call ``listIngredients()`` of ``ConsoleUi.java`` to list all the ingredients in the system.

3. Look at the details of a particular ingredient – the display effect follows the format "egg:piece:4".

4. Delete a particular ingredients – there are two parts in this method, first the program will search if the ingredient exists then delete it with UI notification that the ingredient has been successfully deleted from the system. Furthermore, this method will also traverse all recipe objects, whether any recipe includes the deleted ingredient. If any recipe includes this ingredient, the program will also delete the ingredient from the recipe.

## Recipe Menu Behaviours
1. Add a recipe – to create and add a new recipe to the system is same as add a new ingredient that the program will check if the recipe name already existed in the system. If the recipe name already existed, then the program will not allow the user to add. If the recipe name is unique, the program will directly start to create the recipe. In this design, the ``"amount"`` of recipe's portion is integer and user can define the portion unit. The input for ``"amount"`` will be verificated and only a positive integer number is allowed. After this, user can start to add ingredients for this recipe based on demand. This part is designed from a user-friendly perspective, when the user inputs the ingredient name already exist in the system, the user can directly enter the amount of the ingredient. The program will check if the ingredient is divisible, if it is not, only a positive integer number is allowed for this ingredient; otherwise, user can enter a decimal number. If the ingredient name does not exist in the system, the program will inform the user this ingredient does not exist yet and the program will guide user to create this ingredient (name -> unit -> price). The prompt for adding ingredients will keep asking user to enter new ingredient user enter empty then the adding process will stop. The process for adding comments is very same as above. When enter comments, user adds the wanted comments line by line. The user does not need to add specil mark like ``"*"`` or capital letter for the first word of the line, when write to .txt file, the program will automatically add ``"*"`` in the beginning and change the lower case to upper case.

2. List all available recipes – the program will list all recipes and inform the user the ``"No.x"``(e.g. No.1, No.2 etc) and ``"price"`` of the recipe.

3. Look at a particular recipe – the program achieve all the requirements from instructions. In order to not influencing the original recipe object such as the corresponding ingredients' amounts, this program applys contrustor methods to copy the original object and then modifying the related ingredients' amounts. 

4. Delete a particular recipe – the name for delete is character sensitice i.e. the user must input exact same name as the recipe.

5. Search available recipes (ingredient name, max price, recipe name) – this part firstly implements a search interface then three implementation classes. 
- Search by ingredient name: the user can enter an ingredient, then the system will return all recipes that contain this ingredient.
- Search by max price: the user can enter a maximum accepable recipe price to the program then all the recipes that meet the requirements will be given.
- Search by recipe name: this method achieve a certain level fuzzy search, the user can either input an accurate recipe name or a part of the name. For instance, when user enter ``"egg"``, the program will return ``"Banana egg milk"`` and ``"Egg pie"``.

## Refliction on function realization
This assignment fulfills all the requirements of the passing grade part. The proposed program is finished based on a robust and user-friendly perspective. All user inputs will be verified for the typical processes such as not allowed to enter empty name when creating an ingredient, the entered numbers and digits should be integer or double in certain process etc. For creating a recipe, the program tend to provide a fluent experience when adding new ingredients for the recipe.

## Refliction on JUnit test
There are two test classes in this project: ``IngredientTests.java`` and ``SearchTest.java``. The ``IngredientTests.java`` to show basic understanding of JUnit Test and ``SearchTest.java`` tests more complex search operations. There are two tests for each search method, aiming for test each search method from different perspectives.

## Reflication on insufficient of the assignment
Due to time pressure and inexperience with project management, the most insufficient part of this assignment is that the Gradle was not properly initiated in the beginning stage. This leads to many code quality issues in the end which cannot be solved by deadline. This is considered as a valuable lesson for future work that valuing and improving code quality are very important and should pay extra attention in the initial stage of a project.



