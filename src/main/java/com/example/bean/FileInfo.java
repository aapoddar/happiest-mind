package com.example.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileInfo {

	private long id;
	@JsonIgnore
	  private String name;
	  private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", name=" + name + ", content=" + content + "]";
	}
	
	
	  
	  
}
