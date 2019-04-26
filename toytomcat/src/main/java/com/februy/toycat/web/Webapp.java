package com.februy.toycat.web;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.februy.toycat.handler.WebHandler;

public class Webapp {
	private static WebContent webContent;
	static {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser parser = saxParserFactory.newSAXParser();
			WebHandler handler = new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("web-inf/toyweb.xml"), handler);
			webContent=new WebContent(handler.getServlets(), handler.getServletMappings());
		} catch (Exception e) {
			System.out.println("解析配置文件失败");
		}
	}
	public static Servlet getServlet(String url) {
		Class cls;
		Servlet servlet = null;
		try {
			cls = webContent.getCls("/"+url);
			try {
				servlet=(Servlet) cls.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return servlet;
	}
}
