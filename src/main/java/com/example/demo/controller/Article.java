package com.example.demo.controller;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	
	public Article(int i, String title, String body) {
		
	}
	
	int id;
	String title;
	String body;
	
}
