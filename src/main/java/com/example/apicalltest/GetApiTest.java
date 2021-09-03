package com.example.apicalltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetApiTest {
	public static void main(String[] args) {
		  try{

	            String parms = "bno=&depart=2&applicant=3&userName=4&phoneNum=5&uniqNum=6&bsys_sort=7&use_sort=8&use_period=9";

	            String url = "http://localhost:8080/api/get" + "?"+ parms;

	            URL obj = new URL(url);

	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	            con.setDoInput(true);

	            con.setRequestMethod("GET");
	            con.setRequestProperty("content-type", "application/json");

	            con.setConnectTimeout(10000);
	            con.setReadTimeout(5000);

	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            while ((inputLine=in.readLine())!=null){
	                response.append(inputLine);
	                System.out.println(inputLine);
	            }
	            in.close();

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
  

	}
}
