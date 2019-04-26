package com.februy.toycat.web;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
	
	private BufferedWriter bw;
	private StringBuilder headinfo;
	private StringBuilder content;
	public final String blank=" ";
	public final String CRLF="\r\n";
	private int len;
	public Response() {
		headinfo=new StringBuilder();
		content=new StringBuilder();
		len=0;
	}
	public Response(OutputStream os) {
		this();
		bw=new BufferedWriter(new OutputStreamWriter(os));
	}
	public Response(Socket client) throws IOException {
		this();
		bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	public void print(String info) {
		content.append(info);
		len+=info.getBytes().length;
	};
	public void println(String info) {
		content.append(info).append(CRLF);
		 len+=info.getBytes().length;
	}
	
	public void PushToBrower(int code) throws IOException {
		createHead(code);
		bw.append(headinfo);
		bw.append(content);
		bw.flush();
	}
	public void createHead(int code) {
		headinfo.append("HTTP/1.1").append(blank);
		headinfo.append(code).append(blank);
		switch (code) {
		case 200:
			headinfo.append("OK").append(CRLF);
			break;
		case 404:
			headinfo.append("NOT FOUND").append(CRLF);
			break;
		case 500:
			headinfo.append("SERVER ERROR").append(CRLF);
		default:
			break;
		}
		headinfo.append("Date").append(new Date()).append(CRLF);
		headinfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
		headinfo.append("Content-type:text/html").append(CRLF);
		headinfo.append("Content-Length:").append(len).append(CRLF);
		headinfo.append(CRLF);
		
	}
	
}
