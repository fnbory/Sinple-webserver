package com.februy.toycat.operate;

import com.februy.toycat.web.Request;
import com.februy.toycat.web.Response;
import com.februy.toycat.web.Servlet;

public class RegServlet implements Servlet {

	@Override
	public void service(Request request, Response response) {
		response.print("chengggong");
		
	}



}
