package com.springcloud.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;

public class UrlGetPostRequest {
	
	
	private static final int READ_TIMEOUT = 100000;
	private static final int CONNECT_TIMEOUT = 150000;
	public static final String POST = "POST";
	public static final String GET = "GET";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";
	public static final String HEAD = "HEAD";
	private URL url = null;
	private HttpURLConnection  conn = null;
	private OutputStream os = null;
	private BufferedWriter writer = null;
	private InputStream is = null;
	
	@SuppressWarnings("unused")
	private int responseCode = 0;

	public String request(String method, String url, List<NameValuePair> params) throws IOException {

	    if(params != null && method == GET){
	        url = url.concat("?");
	        url = url.concat(getQuery(params));
	    }

	    this.url = new URL(url);
	    conn = (HttpURLConnection) this.url.openConnection();
	    conn.setReadTimeout(READ_TIMEOUT);
	    conn.setConnectTimeout(CONNECT_TIMEOUT);
	    conn.setRequestMethod(method);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);

	    if(params != null && method == POST){
	        os = conn.getOutputStream();
	        writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
	        writer.write(getQuery(params));
	        writer.flush();
	        writer.close();
	        os.close();
	    }

	    conn.connect();
	    responseCode = conn.getResponseCode();
	    is = conn.getInputStream();
	    String contentAsString = StringFromInputStream.convertStreamToString(is);
	    return contentAsString;
	}



	public String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params){

	        if (first){
	            first = false;
	        } else {
	            result.append("&");
	        }
	        result.append(URLEncoder.encode(pair.getName(),"UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
	    }

	    return result.toString();
	}

}
