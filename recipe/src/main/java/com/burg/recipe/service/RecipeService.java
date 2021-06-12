package com.burg.recipe.service;

import java.util.List;

import com.burg.recipe.business.bean.Recipe;

public interface RecipeService {
	List<Recipe> getAllRecipe();
	int addRecipe(Recipe recipe);
	Recipe updateRecipe(Recipe recipe);
	void deleteRecipe(int recipeId);
	Recipe getRecipeById(int recipeId);
}
