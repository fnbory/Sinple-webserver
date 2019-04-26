package com.februy.toycat.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.februy.toycat.entity.ToyServlet;
import com.februy.toycat.entity.ToyServletMapping;

public class WebContent {
	private Set<ToyServlet> servlets;
	private Set<ToyServletMapping> toyServletMappings;
	private Map<String,String> NameToClass=new HashMap<>();
	private Map<String,String> UrlToName=new HashMap<>();
	public WebContent(Set<ToyServlet> servlets, Set<ToyServletMapping> toyServletMappings) {
		this.servlets = servlets;
		this.toyServletMappings = toyServletMappings;
		for(ToyServlet servlet:servlets) {
			NameToClass.put(servlet.getName(), servlet.getCls().getName());
		}
		for(ToyServletMapping toyServletMapping:toyServletMappings) {
			for(String mapping:toyServletMapping.getMappings()) {
				UrlToName.put(mapping, toyServletMapping.getName());
			}
		}
	}
	
	public  Class getCls(String Url) throws ClassNotFoundException {
		return Class.forName(NameToClass.get(UrlToName.get(Url)));
	}
}
