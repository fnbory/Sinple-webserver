package com.februy.toycat.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.februy.toycat.entity.ToyServlet;
import com.februy.toycat.entity.ToyServletMapping;

public class WebHandler extends DefaultHandler {
	private ToyServlet toyServlet;
	private ToyServletMapping toyServletMapping;
	private Set<ToyServlet> servlets;
	private Set<ToyServletMapping> servletMappings;
	private boolean optservlet;
	private boolean optmapping;
	private String tag;
	public WebHandler() {
		this.servlets=new HashSet<>();
		this.servletMappings=new HashSet<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tag=qName;
		System.out.println("start    "+tag);
		if(tag!=null) {
			if(tag.equals("servlet")) {
				toyServlet=new ToyServlet();
				optservlet=true;
				optmapping=false;
			}
			else if(tag.equals("servlet-mapping")) {
				toyServletMapping=new ToyServletMapping();
				optmapping=true;
				optservlet=false;
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content=new String(ch, start, length);
		System.out.println("content   "+content);
		if(tag!=null) {
			if(optservlet) {
				if(tag.equals("servlet-name")) {
					toyServlet.setName(content);
				}
				else if(tag.equals("servlet-class")) {
					try {
						System.out.println("laile    **");
						Class class1=Class.forName(content);
						toyServlet.setCls(class1);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if(optmapping) {
				if(tag.equals("servlet-name")) {
					toyServletMapping.setName(content);
				}
				else if(tag.equals("url-pattern")) {
					toyServletMapping.addMapping(content);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		tag=null;
		System.out.println("end"+qName);
		if(qName.equals("servlet")) {
			servlets.add(toyServlet);
		}
		else if (qName.equals("servlet-mapping")) {
			servletMappings.add(toyServletMapping);
		}
	}
	
	public Set<ToyServlet> getServlets() {
		return servlets;
	}
	public Set<ToyServletMapping> getServletMappings() {
		return servletMappings;
	}
	
}
