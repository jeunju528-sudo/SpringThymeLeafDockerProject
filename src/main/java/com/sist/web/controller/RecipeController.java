package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.entity.Recipe;
import com.sist.web.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	private final RecipeService service;
	
	/*
	 * @RequestParam :: 단일값
	 * @RequestBody -> @RestController에서 JSON <-> VO로 변환해서 쓸 때 사용
	 * @ModelAttribute : 커맨드 객체, VO 단위로 받는경우
	 * */
	
	@GetMapping("/main/main")
	public String main_main(@RequestParam(value = "page",defaultValue = "1") int page, Model model) {
		
		List<Recipe> list = service.recipeListData(page);
		int[] pages = service.getPageData(page);
		
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		
		model.addAttribute("main_html", "/main/home");
		
		return "main/main";
	}
}
