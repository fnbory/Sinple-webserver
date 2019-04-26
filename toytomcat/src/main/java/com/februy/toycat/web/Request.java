package com.februy.toycat.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Request {
	private String requestinfo;
	private String method;
	private String url;
	private String querystr;
	public final String CRLF="\r\n";
	private Map<String, List<String>> parameterMap;
	public Request(Socket client) throws IOException {
		this(client.getInputStream());
	}
	public Request(InputStream is) {
		parameterMap=new HashMap<>();
		byte[] datas=new byte[1024*1024];
		int len;
		try {
			len = is.read(datas);
			this.requestinfo=new String(datas, 0, len);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		parseRequestInfo(requestinfo);
	}
	public void parseRequestInfo(String requestinfo) {
		System.out.println(requestinfo);
		this.method=requestinfo.substring(0, this.requestinfo.indexOf("/")).toLowerCase();
		System.out.println(method);
		int startindex=requestinfo.indexOf("/")+1;
		int endindex=requestinfo.indexOf("HTTP/");
		this.url=requestinfo.substring(startindex,endindex);
		System.out.println(url);
		int queryIndex=requestinfo.indexOf("?");
		if(queryIndex>0){
			String[] urlArrays=this.url.split("\\?");
			this.url=urlArrays[0];
			System.out.println(url);
			querystr=urlArrays[1];
			System.out.println(querystr);
		}
		
		if(method.equals("post")) {
			String qtr=requestinfo.substring(this.requestinfo.indexOf(CRLF)).trim();
			if(null==querystr) {
				querystr=qtr;
			}
			else {
				querystr+="&"+qtr;
			}
		}
		ConvertMap(querystr);
	}
	private void ConvertMap(String querystr) {
		String[] KeyValues=this.querystr.split("&");
		for(String keyvalue:KeyValues) {
			String[] kv=keyvalue.split("=");
			Arrays.copyOf(kv, 2);
			String key=kv[0];
			String value=kv[1]==null?null:decode(kv[1], "utf-8");
			if(!parameterMap.containsKey(key)) {
				parameterMap.put(key, new ArrayList<String>());
			}
			parameterMap.get(key).add(value);
		}
	}
	
	public String[] getParametersValues(String key) {
		List<String> values=parameterMap.get(key);
		if(values==null||values.size()<1) {
			return null;
		}
		return  values.toArray(new String[0]);
	}
	
	public String decode(String value,String enc) {
		try {
			return java.net.URLDecoder.decode(value,enc );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getPatameter(String key) {
		String[] values=getParametersValues(key);
		return values==null?null:values[0];
	}
	public String getUrl() {
		return url;
	}
	public String getQuerystr() {
		return querystr;
	}
	
}
