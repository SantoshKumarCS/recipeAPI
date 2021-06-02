package com.burg.recipe.entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="recipe")
public class RecipeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@NotBlank
	@Column(name="recipe_name")
    private String recipeName;
	
	@Column(name="created_date")
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date createdDate;
	
	@Column(name="is_veg")
	private Boolean isVeg = false;
	
	@Column(name="suitable_for")
    private String suitableFor;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="recipe_id", referencedColumnName="id")
	private List<IngredientEntity> ingredients = new ArrayList<>();
	
	public RecipeEntity() {
		
	}
	
	public RecipeEntity(@NotBlank String recipeName, Date createdDate, Boolean isVeg, String suitableFor,
			String cookingInstructions) {
		super();
		this.recipeName = recipeName;
		this.createdDate = createdDate;
		this.isVeg = isVeg;
		this.suitableFor = suitableFor;
		this.cookingInstructions = cookingInstructions;
	}

	public List<IngredientEntity> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientEntity> list) {
		this.ingredients = list;
	}
	@Column(name="cooking_instructions",length=6000)
    private String cookingInstructions;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
