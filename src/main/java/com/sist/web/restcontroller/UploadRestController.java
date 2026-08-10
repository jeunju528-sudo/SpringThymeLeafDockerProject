package com.sist.web.restcontroller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadRestController {

	// yml 값 읽기
	@Value("${file.upload_dir}")
	private String uploadDir;

	private static int COUNT = 1;

	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

		File f = new File(uploadDir);
		if (!f.exists()) {
			f.mkdir(); // 디렉터리가 없다면 디렉터리를 만들기
		}

		if (file.isEmpty()) {
			return "no file!";
		}

		String originName = file.getOriginalFilename(); // 사용자가 보내주는 파일명
		File files = new File(uploadDir + "/" + originName);

		String newName = ""; // 업로드 할 파일명
		if (files.exists()) { // 같은 파일명의 파일이 이미 있다면
			String name = originName.substring(0, originName.lastIndexOf(".")); // 파일명
			String ext = originName.substring(originName.lastIndexOf(".")); // 확장자

			newName = name + "(" + COUNT + ")" + ext;
			COUNT++;
		} else {
			newName = originName;
		}

		Path savePath = Paths.get(uploadDir, newName);
		Files.copy(file.getInputStream(), savePath);

		return "upload success: " + originName + ", 변경: " + newName;
	}

	@PostMapping("/multi_upload")
	public String multi_upload(@RequestParam(value = "files", required = false) List<MultipartFile> files) throws Exception {
		
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				return "no file!";
			}
			else {
				String originName = file.getOriginalFilename();
				System.out.println(originName);
				File f = new File(uploadDir + "/" +originName);
				if (f.exists()) { // 같은 파일명의 파일이 이미 있다면
					String name = originName.substring(0, originName.lastIndexOf(".")); // 파일명
					String ext = originName.substring(originName.lastIndexOf(".")); // 확장자
					int cnt = 1;
					while(f.exists()) { // aaa.txt aaa(1).txt aaa(2).txt 이렇게 찾기위해 돌리는 것
						String newName = name + "(" + cnt + ")" + ext;
						System.out.println(newName);
						f = new File(uploadDir + "/" +newName);
						cnt++;
					}
				}
				Path savePath = Paths.get(uploadDir, f.getName());
				Files.copy(file.getInputStream(), savePath);
			}
		}
		
		return "multiple upload success";
	}

}
