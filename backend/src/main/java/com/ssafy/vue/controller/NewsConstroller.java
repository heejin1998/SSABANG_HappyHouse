package com.ssafy.vue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/news")
public class NewsConstroller {

    @GetMapping
    public Map getNews() {
        String clientId = "WyakSOJnCi5vjkJjlILb";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "sl3QbUc5Te";//애플리케이션 클라이언트 시크릿값";
        Map map = null;
        StringBuffer response = null;
        try {
            String text = URLEncoder.encode("부동산", "UTF-8"); //검색어";
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+ text; // 뉴스의 json 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(response.toString(), Map.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        

        return map;
    }

}
