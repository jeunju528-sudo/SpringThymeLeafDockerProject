package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Chef {
	@Id
	private String chef;
	private String poster;
	private String mem_cont1;
	private String mem_cont3;
	private String mem_cont7;
	private String mem_cont2;
}