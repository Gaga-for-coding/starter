package com.guissisoftware.starter.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Course {
	private long id;
	private String name;
	private String category;
	@Min(value = 1, message = "A course should have a minimum of 1 rating")
	@Max(value = 5, message = "A course should have a maximum of 5 rating")
	private int rating;
	private String description;

	public Course(long id, String name, String category, int rating, String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.description = description;
	}

	public Course() {
	}

	public long id() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String name() {
		return name;
	}

	public Course setName(String name) {
		this.name = name;
		return this;
	}

	public String category() {
		return category;
	}

	public Course setCategory(String category) {
		this.category = category;
		return this;
	}

	public int rating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String description() {
		return description;
	}

	public Course setDescription(String description) {
		this.description = description;
		return this;
	}
}
