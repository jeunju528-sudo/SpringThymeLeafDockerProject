package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Recipe {
	@Id
	private int no;
	private String title;
	private String poster;
	private String chef;
	private String link;
	private int hit;
}
