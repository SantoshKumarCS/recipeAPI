package com.burg.recipe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burg.recipe.entity.IngredientEntity;

public interface IngredientsDAO extends JpaRepository<IngredientEntity, Integer>{

}
