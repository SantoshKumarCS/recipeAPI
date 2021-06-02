package com.burg.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burg.recipe.business.bean.Recipe;
import com.burg.recipe.dao.RecipeDAOWrapper;

@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeDAOWrapper recipeDAOWrapper;

	@Override
	public List<Recipe> getAllRecipe() {
		return recipeDAOWrapper.getAllRecipe();
	}

	@Override
	public int addRecipe(Recipe recipe) {
		return recipeDAOWrapper.addRecipe(recipe);
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) throws Exception {
		return recipeDAOWrapper.updateRecipe(recipe);
	}

	@Override
	public void deleteRecipe(int recipeId) throws Exception {
		recipeDAOWrapper.deleteRecipe(recipeId);
	}

}
