package com.example.apicalltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostApiTest {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String url = "http://localhost:8080/api/post";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("content-type", "application/json");

        // 데이터
        String json = "{\"bno\": \"222\", \"depart\" : \"wr\", \"applyer\" : \"wqr\", \"userName\" : \"wqr\", \"phoneNum\" : \"wqrwr\", \"uniqNum\" : \"wqr\", \"bsys_sort\" : \"qwrwq\", \"use_sort\" : \"qwrqr\"}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(json, Map.class);

        // 전송
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());

        try {
            osw.write(json);
            osw.flush();

            // 응답
            BufferedReader br = null;

            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            // 닫기
            osw.close();
            br.close();
        } catch (MalformedURLException e) {
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
