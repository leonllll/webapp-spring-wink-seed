package com.leonlu.code.sample.webapp.ws.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class People {
	@JsonProperty(access = Access.READ_ONLY) //Jackson JSON on deserialize only
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer age;
	
	public People() { //for jpa only		
	}
	
	public People(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
