package com.burg.recipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.burg.recipe.entity.RecipeEntity;

@Repository
public class RecipeDAOWrapper {

	@Autowired
	private RecipeDAO recipeDAO;
	
	//Code to fetch list of recipes from database 
	public List<RecipeEntity> getAllRecipe(){
		List<RecipeEntity> recipeEntityList= recipeDAO.findAll();
		return recipeEntityList;
	}
	
	//Code to add a recipe to database 
	public int addRecipe(RecipeEntity recipeEntity) {
		RecipeEntity entity2=recipeDAO.save(recipeEntity);
		return entity2.getId();
	}
	
	//Code to find a recipe by id
	public RecipeEntity getRecipeById(int recipeId) {
		RecipeEntity recipeEntity = recipeDAO.findById(recipeId).get();
		return recipeEntity;
	} 
	
	//Code to update a recipe in database
	public RecipeEntity updateRecipe(RecipeEntity recipeEntity) throws Exception{
		RecipeEntity entity=recipeDAO.save(recipeEntity);
		return entity;
	}
	
	//Code to delete a recipe in database
	public void deleteRecipe(int recipeId){
		RecipeEntity recipeEntity = recipeDAO.findById(recipeId).get();
		recipeDAO.delete(recipeEntity);
	}
}
