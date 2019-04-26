package com.februy.toycat.entity;

import java.util.HashSet;
import java.util.Set;

public class ToyServletMapping {
	private String name;
	private Set<String> mappings=new HashSet<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getMappings() {
		return mappings;
	}
	public void setMappings(Set<String> mappings) {
		this.mappings = mappings;
	}
	public ToyServletMapping() {
		super();
	}
	public void addMapping(String mapping) {
		mappings.add(mapping);
	}
}
