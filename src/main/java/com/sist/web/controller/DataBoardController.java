package com.sist.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sist.web.service.DataboardService;
import com.sist.web.vo.DataBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DataBoardController {
	private final DataboardService databoardService;
	
	@GetMapping("/databoard/list")
	public String databoard_list(@RequestParam(value="page", defaultValue = "1") int page, Model model) {
		
		int start = (page-1)*10;
		List<DataBoardVO> list = databoardService.databoardListData(start);
		int totalpage = databoardService.databoardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", page);
		model.addAttribute("totalpage", totalpage);
		
		model.addAttribute("main_html", "databoard/list");
		return "main/main";
	}
	
	@GetMapping("/databoard/insert")
	public String databoard_insert(Model model) {
		model.addAttribute("main_html", "databoard/insert");
		return "main/main";
	}
	
	@PostMapping("/databoard/insert_ok")
	public String databoard_insert_ok(@ModelAttribute("vo") DataBoardVO vo, HttpServletRequest request) throws Exception{
		
		String uploadDir = request.getServletContext().getRealPath("/upload");
		System.out.println("uploadDir :: " + uploadDir);
		File dir = new File(uploadDir);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		List<MultipartFile> files = vo.getFiles();

		String filename = "";
		String filesize= "";
		boolean bChk = false;
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				bChk = false;
			}
			else {
				String originName = file.getOriginalFilename();
				File f = new File(uploadDir, originName); // File f = new File(uploadDir+"//"+originName);
				if(f.exists()) {
					String name = originName.substring(0,originName.lastIndexOf("."));
					String ext = originName.substring(originName.lastIndexOf("."));
					int count = 1;
					while(f.exists()) {
						String newName = name+"("+count+")";
						f = new File(uploadDir+"/"+newName);
						count++;
					}
				}
				// Paths :: File과 비슷하게 경로를 표현하는 객체, 슬래시(/, \) 같은 OS별 구분자를 알아서 처리
				Path path = Paths.get(uploadDir, f.getName()); // uploadDir(폴더 경로)와 f.getName()(파일명)을 합쳐서 최종 저장 경로를 만듦
				
				// file.getInputStream() : 업로드된 파일의 실제 데이터(바이트 스트림)를 읽어오는 통로
				Files.copy(file.getInputStream(), path);		// // 그 경로에 내용 써넣기
				filename+=f.getName()+",";
				filesize+=f.length()+","; // File이 size를 갖고있지 않고, length()를 호출할 때마다 OS에 실제 파일 크기를 물어봐서 돌려줌
				bChk = true;
			}
		}
		
		if(bChk) {
			filename=filename.substring(0,filename.lastIndexOf(","));
			filesize=filesize.substring(0,filesize.lastIndexOf(","));
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			vo.setFilecount(files.size());
			
		}
		else {
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}
		
		databoardService.databoardInsert(vo);
		
		return "redirect:/databoard/list";
	}
	
}
