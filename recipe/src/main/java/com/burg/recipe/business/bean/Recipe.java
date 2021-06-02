package com.burg.recipe.business.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Recipe {
	
	private Integer id;
    private String recipeName;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date createdDate;
    private Boolean isVeg = false;
    private String suitableFor;
    private String cookingInstructions;
    private List<Ingredients> ingredients;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Ingredients> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public Boolean getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}
	public String getSuitableFor() {
		return suitableFor;
	}
	public void setSuitableFor(String suitableFor) {
		this.suitableFor = suitableFor;
	}
	public String getCookingInstructions() {
		return cookingInstructions;
	}
	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}
	

}
