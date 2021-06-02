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
import com.burg.recipe.service.RecipeService;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping(value="recipe/get",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> getAllRecipe(){
		List<Recipe> list = recipeService.getAllRecipe();
		return new ResponseEntity<List<Recipe>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value="recipe/add",consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addRecipe(@Valid @RequestBody Recipe recipe){
		int id = recipeService.addRecipe(recipe);
		return new ResponseEntity<String>("Recipe added Successfully"+ id ,HttpStatus.OK);
	}
	
	@PutMapping(value="recipe/update",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe){
		Recipe recipe2=null;
		try {
			recipe2=recipeService.updateRecipe(recipe);
			return new ResponseEntity<Recipe>(recipe2,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Recipe>(recipe2,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="recipe/delete/{id}",produces=MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> deleteRecipe(@PathVariable("id") int id){
		String result=null;
		try {
			recipeService.deleteRecipe(id);
			return new ResponseEntity<String>("Recipe deleted Successfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
