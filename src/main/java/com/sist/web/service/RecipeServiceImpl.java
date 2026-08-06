package com.sist.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.web.entity.Recipe;
import com.sist.web.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;

	@Override
	public List<Recipe> findByTitleContains(String title) {
		return null;
	}

	@Override
	public List<Recipe> findByChefContains(String chef) {
		return null;
	}

	@Override
	public List<Recipe> recipeListData(int page) {
		Pageable pg = PageRequest.of(page-1, 12, Sort.by("no").ascending());
		Page<Recipe> pList = recipeRepository.findAll(pg);
		List<Recipe> list = new ArrayList<>();
		if(pList.hasNext() && !pList.isEmpty()) {
			list = pList.getContent();
		}
		return list;
	}

	@Override
	public int[] getPageData(int page) {
		int totalpage = (int)(Math.ceil(recipeRepository.count()/12.0));
		int startpage = ((page-1)/10*10)+1;
		int endpage = ((page-1)/10*10)+10;
		if(endpage > totalpage)
			endpage = totalpage;
		int[] pages = {page,totalpage,startpage,endpage};
		return pages;
	}
	
}
