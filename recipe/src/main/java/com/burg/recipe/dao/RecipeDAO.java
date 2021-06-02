package com.burg.recipe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burg.recipe.entity.RecipeEntity;

public interface RecipeDAO extends JpaRepository<RecipeEntity, Integer>{

}
