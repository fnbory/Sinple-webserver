package com.februy.toycat.operate;

import java.io.IOException;

import com.februy.toycat.web.Request;
import com.februy.toycat.web.Response;
import com.februy.toycat.web.Servlet;

public class LoginServlet implements Servlet {

	@Override
	public void service(Request request, Response response) {
		response.print("<html>");
		response.print("<head>");
		response.print("<title>");
		response.print("第一个servlet");
		response.print("</title>");
		response.print("</head>");
		response.print("<body>");
		response.print("欢迎回来");
		response.print("</body>");
		response.print("<html>");
	}

	

}
