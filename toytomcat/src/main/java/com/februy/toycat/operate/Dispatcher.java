package com.februy.toycat.operate;

import java.io.IOException;
import java.net.Socket;

import com.februy.toycat.web.Request;
import com.februy.toycat.web.Response;
import com.februy.toycat.web.Servlet;
import com.februy.toycat.web.Webapp;

public class Dispatcher implements Runnable{
	private Socket client;
	private Request request;
	private Response response;
	public  Dispatcher(Socket client) {
		this.client=client;
		try {
			request=new Request(client);
			response=new Response(client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			release();
		}
	}

	@Override
	public void run() {
		Servlet servlet=Webapp.getServlet(request.getUrl());
		try {
			if(null!=servlet) {
				servlet.service(request, response);
				response.PushToBrower(200);
			}
			else {
				response.PushToBrower(404);
			}
		}
		catch (Exception e) {
			try {
				response.PushToBrower(500);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		release();
	}
	private void release() {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
