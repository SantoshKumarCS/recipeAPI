package com.burg.recipe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredients")
public class IngredientEntity {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private String description;
	    
	    public IngredientEntity() {
	    	
	    }

		public IngredientEntity(String description) {
			super();
			this.description = description;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
}
