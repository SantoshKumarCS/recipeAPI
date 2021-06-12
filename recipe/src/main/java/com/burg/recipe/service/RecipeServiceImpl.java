package com.burg.recipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burg.recipe.business.bean.Ingredients;
import com.burg.recipe.business.bean.Recipe;
import com.burg.recipe.custom.exception.BusinessException;
import com.burg.recipe.custom.exception.EmptyListException;
import com.burg.recipe.dao.RecipeDAOWrapper;
import com.burg.recipe.entity.IngredientEntity;
import com.burg.recipe.entity.RecipeEntity;

//defining the business logic
@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeDAOWrapper recipeDAOWrapper;

	@Override
	public List<Recipe> getAllRecipe() {
		List<RecipeEntity> recipeEntityList=null;
		List<Recipe> beanList =null;
		try {
			recipeEntityList=recipeDAOWrapper.getAllRecipe();
			beanList = new ArrayList<>();
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
		}catch(Exception e) {
			throw new BusinessException("605", "Something went wrong in service layer while fetching list of recipes"+e.getMessage());
		}
		if(recipeEntityList.isEmpty())
			throw new EmptyListException("604","Recipe list is empty, please add some recipes using add functionality");
		return beanList;
	}

	@Override
	public int addRecipe(Recipe recipe) {

		if(recipe.getRecipeName().isEmpty() || recipe.getRecipeName().length()==0) {
			throw new BusinessException("601","Please provide recipe name");
		}
		try {
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
			return recipeDAOWrapper.addRecipe(entity);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","Given Recipe is null"+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","Something went wrong in Business layer"+e.getMessage());
		}
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) {
		RecipeEntity recipeEntity = recipeDAOWrapper.getRecipeById(recipe.getId());
		RecipeEntity entity;
		Recipe recipe2= new Recipe();
		try {
			if(null!=recipeEntity) {
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
				entity=recipeDAOWrapper.updateRecipe(recipeEntity);
				recipe2.setIngredients(ingredientsList);
				BeanUtils.copyProperties(entity, recipe2);
			}
			return recipe2;
		}catch (Exception e) {
			throw new BusinessException("619","Something went wrong in Business layer"+e.getMessage());
		}
	}

	@Override
	public void deleteRecipe(int recipeId){
		try {
			recipeDAOWrapper.deleteRecipe(recipeId);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("608", "given recipeId is null, please provide some id"+e.getMessage());
		}catch(NoSuchElementException e) {
			throw new BusinessException("609", "given recipeId doesnot exist in db "+e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("620","Something went wrong in Business layer"+e.getMessage());
		}
	}

	@Override
	public Recipe getRecipeById(int recipeId) {
		try {
			RecipeEntity recipeEntity = recipeDAOWrapper.getRecipeById(recipeId);
			Recipe recipe= new Recipe();
			List<Ingredients> ingList = new ArrayList<>();
			for(IngredientEntity ing: recipeEntity.getIngredients()) {
				Ingredients entity2 = new Ingredients();
				BeanUtils.copyProperties(ing, entity2);
				ingList.add(entity2);
			}
			recipe.setIngredients(ingList);
			BeanUtils.copyProperties(recipeEntity, recipe);
			return recipe;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("606", "given recipeId is null, please provide some id"+e.getMessage());
		}catch(NoSuchElementException e) {
			throw new BusinessException("607", "given recipeId doesnot exist in db "+e.getMessage());
		}
	}

}
