package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	public List<Recipe> findByTitleContains(String title);
	public List<Recipe> findByChefContains(String chef);
}
