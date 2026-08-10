package com.sist.web.vo;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/*
 * NO        NOT NULL NUMBER         
NAME      NOT NULL VARCHAR2(51)   
SUBJECT   NOT NULL VARCHAR2(2000) 
CONTENT   NOT NULL CLOB           
PWD       NOT NULL VARCHAR2(10)   
REGDATE            DATE           
HIT                NUMBER         
FILENAME           VARCHAR2(1000) 
FILESIZE           VARCHAR2(500)  
FILECOUNT          NUMBER   
 * */
@Data
public class DataBoardVO {
	private int no, hit, filecount;
	private String name, subject, content, pwd, filename, filesize, dbday;
	private Date regdate;
	private List<MultipartFile> files;
}
