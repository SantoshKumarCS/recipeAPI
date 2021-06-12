package com.burg.recipe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.burg.recipe.business.bean.Recipe;
import com.burg.recipe.custom.exception.BusinessException;
import com.burg.recipe.custom.exception.ControllerException;
import com.burg.recipe.service.RecipeService;

@RestController
public class RecipeController {

	//autowire the RecipeService class
	@Autowired
	private RecipeService recipeService;
	
	
	/*
	 * get mapping request that retrieves all the recipe detail from the database
	 * code is not surrounded with try catch block to demonstrate the use of Global
	 * exception will throw error message from global exception class when list is empty
	 */
	@GetMapping(value="recipe/get",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> getAllRecipe(){
		List<Recipe> list = recipeService.getAllRecipe();
		return new ResponseEntity<List<Recipe>>(list,HttpStatus.OK);
	}

	/*
	 * get mapping request that retrieves recipe detail w.r.t the id given from the database
	 * code is surrounded with try catch block to demonstrate the use of custom exception
	 */
	@GetMapping(value="recipe/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecipeById(@PathVariable("id") int id){
		try {
			Recipe list = recipeService.getRecipeById(id);
			return new ResponseEntity<Recipe>(list,HttpStatus.OK);
		}catch(BusinessException e) {
			ControllerException c = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException c = new ControllerException("612", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
	}

	//creating post mapping that post the recipe detail in the database
	@PostMapping(value="recipe/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRecipe(@Valid @RequestBody Recipe recipe){
		try {
			int id = recipeService.addRecipe(recipe);
			return new ResponseEntity<String>("Recipe added Successfully"+ id ,HttpStatus.OK);
		}catch(BusinessException e) {
			ControllerException c = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException c = new ControllerException("611", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
	}


	/*
	 * put mapping to update the recipe request from the user 
	 * not surronded with try
	 * catch to demonstrate example the use of global exception when given id not
	 * present in db
	 */ 
	@PutMapping(value="recipe/update",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe){
		Recipe recipe2=null;
		recipe2=recipeService.updateRecipe(recipe);

		return new ResponseEntity<Recipe>(recipe2,HttpStatus.OK);
	}

	//creating a delete mapping that deletes a specified recipe 
	@DeleteMapping(value="recipe/delete/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable("id") int id){
		try {
			recipeService.deleteRecipe(id);
		}catch(BusinessException e) {
			ControllerException c = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException c = new ControllerException("615", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(c,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Recipe deleted Successfully", HttpStatus.OK);

	}

}
