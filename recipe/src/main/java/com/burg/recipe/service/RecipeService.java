package com.burg.recipe.service;

import java.util.List;

import com.burg.recipe.business.bean.Recipe;

public interface RecipeService {
	List<Recipe> getAllRecipe();
	int addRecipe(Recipe recipe);
	Recipe updateRecipe(Recipe recipe) throws Exception;
	void deleteRecipe(int recipeId) throws Exception;
}
