package com.burg.recipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.burg.recipe.business.bean.Ingredients;
import com.burg.recipe.business.bean.Recipe;
import com.burg.recipe.controller.RecipeController;
import com.burg.recipe.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private RecipeService recipeService;
	
	@Test
	@WithMockUser(username = "burg", password = "burg", roles = "USER")
	public void testGetRecipes() throws Exception {
		List<Recipe> listRecipies= new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setCreatedDate(new Date());
		recipe.setCookingInstructions("description");
		recipe.setIsVeg(true);
		recipe.setRecipeName("Dosa");
		recipe.setSuitableFor("for everyone");
		recipe.setId(1);
		List<Ingredients> ingredientsList =new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setDescription("Potato");
		ingredients.setId(1);
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		listRecipies.add(recipe);
		Mockito.when(recipeService.getAllRecipe()).thenReturn(listRecipies);
		
		String url = "/recipe/get";
		
		MvcResult mvcResult=mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println("actual"+actualJsonResponse);
		
		String expectedJsonResponse= objectMapper.writeValueAsString(listRecipies);
		System.out.println("expected"+expectedJsonResponse);
		
		assertThat(actualJsonResponse).isEqualToIgnoringCase(expectedJsonResponse);
	}
	
	@Test
	@WithMockUser(username = "burg", password = "burg", roles = "USER")
	public void testAddRecipe() throws JsonProcessingException, Exception {
		Recipe recipe = new Recipe();
		recipe.setCreatedDate(new Date());
		recipe.setCookingInstructions("description");
		recipe.setIsVeg(true);
		recipe.setRecipeName("Dosa");
		recipe.setSuitableFor("for everyone");
		List<Ingredients> ingredientsList =new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setDescription("Potato");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		
		Recipe savedRecipe = new Recipe();
		savedRecipe.setCreatedDate(new Date());
		savedRecipe.setCookingInstructions("description");
		savedRecipe.setIsVeg(true);
		savedRecipe.setRecipeName("Dosa");
		savedRecipe.setSuitableFor("for everyone");
		savedRecipe.setId(1);
		List<Ingredients> savedIngredientsList =new ArrayList<>();
		Ingredients savedIngredients = new Ingredients();
		savedIngredients.setDescription("Potato");
		savedIngredients.setId(1);
		savedIngredientsList.add(savedIngredients);
		savedRecipe.setIngredients(savedIngredientsList);
		
		Mockito.when(recipeService.addRecipe(recipe)).thenReturn(savedRecipe.getId());
		
		String url = "/recipe/add";
		mockMvc.perform(
				post(url).contentType("application/json")
				.content(objectMapper.writeValueAsString(recipe))
				).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "burg", password = "burg", roles = "USER")
	public void testRecipeNameMustNotBeBlank() throws JsonProcessingException, Exception {
		Recipe recipe = new Recipe();
		recipe.setCreatedDate(new Date());
		recipe.setCookingInstructions("description");
		recipe.setRecipeName("");
		recipe.setIsVeg(true);
		recipe.setSuitableFor("for everyone");
		List<Ingredients> ingredientsList =new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setDescription("Potato");
		ingredients.setId(1);
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		
		String url = "/recipe/add";
		mockMvc.perform(
				post(url).contentType("application/json")
				.content(objectMapper.writeValueAsString(recipe))
				).andExpect(status().isOk())
				.andDo(print());
		
		Mockito.verify(recipeService, times(0)).addRecipe(recipe);
		
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testUpdateRecipe() throws JsonProcessingException, Exception {
		Recipe recipe = new Recipe();
		recipe.setCreatedDate(new Date());
		recipe.setCookingInstructions("description");
		recipe.setIsVeg(true);
		recipe.setRecipeName("Dosa");
		recipe.setSuitableFor("for everyone");
		recipe.setId(1);;
		List<Ingredients> ingredientsList =new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setDescription("Potato");
		ingredients.setId(1);
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		
		Recipe savedRecipe = new Recipe();
		savedRecipe.setCreatedDate(new Date());
		savedRecipe.setCookingInstructions("description");
		savedRecipe.setIsVeg(true);
		savedRecipe.setRecipeName("Dosa");
		savedRecipe.setSuitableFor("for everyone");
		savedRecipe.setId(1);
		List<Ingredients> savedIngredientsList =new ArrayList<>();
		Ingredients savedIngredients = new Ingredients();
		savedIngredients.setDescription("Potato");
		savedIngredients.setId(1);
		savedIngredientsList.add(savedIngredients);
		savedRecipe.setIngredients(savedIngredientsList);
		
		Mockito.when(recipeService.addRecipe(recipe)).thenReturn(savedRecipe.getId());
		
		String url = "/recipe/update";
		System.out.println("obj"+ objectMapper.writeValueAsString(recipe));
		mockMvc.perform(
				put(url).contentType("application/json")
				.content(objectMapper.writeValueAsString(recipe))
				).andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testDeleteRecipe() throws Exception {
		Integer recipeId = 1;
		Mockito.doNothing().when(recipeService).deleteRecipe(recipeId);
		String url = "/recipe/delete/"+recipeId;
		mockMvc.perform(delete(url)).andExpect(status().isOk());
		Mockito.verify(recipeService,times(1)).deleteRecipe(recipeId);
	}

}
