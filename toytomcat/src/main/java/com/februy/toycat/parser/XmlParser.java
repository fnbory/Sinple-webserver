package com.februy.toycat.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.februy.toycat.entity.ToyServlet;
import com.februy.toycat.handler.WebHandler;
import com.februy.toycat.web.Servlet;
import com.februy.toycat.web.WebContent;

public class XmlParser {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = saxParserFactory.newSAXParser();
		WebHandler handler = new WebHandler();
		parser.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("web-inf/toyweb.xml"), handler);
		WebContent webContent=new WebContent(handler.getServlets(), handler.getServletMappings());
		Class class1=webContent.getCls("/reg");
		Servlet servlet=(Servlet) class1.newInstance();
	}
}
