package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoEntity {

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String task;
	private boolean isCompleted;
}
