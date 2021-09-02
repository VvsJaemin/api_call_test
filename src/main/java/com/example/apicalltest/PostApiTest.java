package com.example.apicalltest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostApiTest {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/postcall";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("content", "application/json");

        // String 타입의 json 데이터를 map으로 변환
        String json = "{\"bno\": \"ss\", \"depart\" : \"9452\", \"applyer\" : \"9452\", \"userName\" : \"9452\", \"phoneNum\" : \"9452\", \"uniqNum\" : \"9452\", \"bsys_sort\" : \"9452\", \"use_sort\" : \"9452\"}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(json, Map.class);


        //데이터 전송
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());

        try{
            osw.write(json);
            osw.flush();

            BufferedReader br =null;
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            osw.close();
            br.close();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
