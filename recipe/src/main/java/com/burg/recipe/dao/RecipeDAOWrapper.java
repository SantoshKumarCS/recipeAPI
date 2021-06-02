package com.burg.recipe.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.burg.recipe.business.bean.*;
import com.burg.recipe.entity.IngredientEntity;
import com.burg.recipe.entity.RecipeEntity;

@Repository
public class RecipeDAOWrapper {

	@Autowired
	private RecipeDAO recipeDAO;
	
	public List<Recipe> getAllRecipe(){
		List<RecipeEntity> recipeEntityList= recipeDAO.findAll();
		List<Recipe> beanList = new ArrayList<>();
		for(RecipeEntity recipeEntity : recipeEntityList) {
			Recipe recipe = new Recipe();
			List<Ingredients> ingList = new ArrayList<>();
			for(IngredientEntity ing: recipeEntity.getIngredients()) {
				Ingredients entity2 = new Ingredients();
				BeanUtils.copyProperties(ing, entity2);
				ingList.add(entity2);
			}
			recipe.setIngredients(ingList);
			BeanUtils.copyProperties(recipeEntity, recipe);
			beanList.add(recipe);
		}
		return beanList;
	}
	
	public int addRecipe(Recipe recipe) {
		RecipeEntity entity = new RecipeEntity();
		List<Ingredients> ingredientEntity = recipe.getIngredients();
		List<IngredientEntity> ingredientList = new ArrayList<>();
		for(Ingredients ingredients: ingredientEntity) {
			IngredientEntity entity2=new IngredientEntity();
			BeanUtils.copyProperties(ingredients, entity2);
			ingredientList.add(entity2);
		}

		entity.setIngredients(ingredientList);
		BeanUtils.copyProperties(recipe, entity);
		
		RecipeEntity entity2=recipeDAO.save(entity);
		return entity2.getId();
	}
	
	public Recipe updateRecipe(Recipe recipe) throws Exception{
		RecipeEntity recipeEntity = recipeDAO.findById(recipe.getId()).get();
		RecipeEntity entity;
		Recipe recipe2= new Recipe();
		if(recipeEntity!=null) {
			recipeEntity.setRecipeName(recipe.getRecipeName());
			recipeEntity.setCreatedDate(recipe.getCreatedDate());
			recipeEntity.setIsVeg(recipe.getIsVeg());
			recipeEntity.setSuitableFor(recipe.getSuitableFor());
			recipeEntity.setCookingInstructions(recipe.getCookingInstructions());
			
			  List<Ingredients> ingredientsList= recipe.getIngredients();
			  List<IngredientEntity> entityList= new ArrayList<>(); 
			  for(Ingredients ing: ingredientsList) { 
			  IngredientEntity entity2 = new IngredientEntity();
			  
			  BeanUtils.copyProperties(ing, entity2); 
			  entityList.add(entity2); 
			  }
			  recipeEntity.setIngredients(entityList);
			 
			entity=recipeDAO.save(recipeEntity);
			recipe2.setIngredients(ingredientsList);
			BeanUtils.copyProperties(entity, recipe2);
		}else {
			throw new Exception("Details does not exist");
		}
		return recipe2;
	}
	
	public void deleteRecipe(int recipeId) throws Exception{
		RecipeEntity recipeEntity = recipeDAO.findById(recipeId).get();
		if(recipeEntity!=null) {
			recipeDAO.delete(recipeEntity);
		}else {
			throw new Exception("Details does not exist");
		}
	}
}
