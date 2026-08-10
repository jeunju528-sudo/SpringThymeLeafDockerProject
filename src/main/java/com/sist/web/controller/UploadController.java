package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {
	@GetMapping("/upload")
	public String upload_page(Model model) {
		
		model.addAttribute("main_html", "upload");
		return "main/main";
	}
	
	@GetMapping("/upload2")
	public String upload_page2(Model model) {
		
		model.addAttribute("main_html", "upload2");
		return "main/main";
	}
	
	@GetMapping("/upload3")
	public String upload_page3(Model model) {
		
		model.addAttribute("main_html", "upload3");
		return "main/main";
	}
	
	/*
	@GetMapping("/upload2")
	public String upload_page2() {
		return "upload2";
	}
	*/
}
