package com.ssafy.vue.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.vue.dto.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

    @Autowired
    private HouseMapMapper houseMapMapper;

    @Override
    public List<SidoGugunCodeDto> getSido() throws Exception {
        return houseMapMapper.getSido();
    }

    @Override
    public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
        return houseMapMapper.getGugunInSido(sido);
    }

    @Override
    public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
        return houseMapMapper.getDongInGugun(gugun);
    }

    @Override
    public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
        return houseMapMapper.getAptInDong(dong);
    }

    @Override
    public List<HouseInfoDto> getAptLatLng(double swLat, double swLng, double neLat, double neLng) throws Exception {
        List<HouseInfoDto> houseInfoList = houseMapMapper.getAptLatLng(swLat, swLng, neLat, neLng);
        for(HouseInfoDto houseInfo : houseInfoList){
            String recentPrice = houseMapMapper.getRecentPrice(houseInfo.getAptCode());
            houseInfo.setRecentPrice(recentPrice);
        }
        return houseInfoList;
    }

    @Override
    public List<HouseInfoDto> getAptLatLngWithDeals(double swLat, double swLng, double neLat, double neLng) throws Exception {
        List<HouseInfoDto> houseInfoList = houseMapMapper.getAptLatLng(swLat, swLng, neLat, neLng);
        for(HouseInfoDto houseInfo : houseInfoList){
            String recentPrice = houseMapMapper.getRecentPrice(houseInfo.getAptCode());
            houseInfo.setRecentPrice(recentPrice);
            houseInfo.setHouseDeals(houseMapMapper.getHouseDeal(houseInfo.getAptCode()));
        }
        return houseInfoList;
    }
    
    @Override
	public List<Facility> getDistance(String type, int aptCode) throws IOException {
		//aptCode에 해당하는 경도, 위도 얻기
		HouseInfoDto info = houseMapMapper.getHouseInfo(aptCode);
		String x=info.getLng();
		String y =info.getLat();
		
		String REST_KEY = "820050bdaece0724c93dd4bd8274b037";
		
		//API 호출 URI
		String URI = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code="+type+"&"
				+ "x="+x+"&y="+y+"&radius=1000&sort=distance";
		
		BufferedReader br = null;
		JSONObject json = new JSONObject();
		
		//URI 실행
		URL url;
		try {
			url = new URL(URI);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Authorization", "KakaoAK "+REST_KEY);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		json = new JSONObject(br.readLine());
		//documents 속성의 내용을 JSONObject로 갖고오기
		JSONArray arr = json.getJSONArray("documents");
		List<Facility> list = new ArrayList<>();
		for(int i=0;i<arr.length();i++) {
			JSONObject data = arr.getJSONObject(i);
			String id = data.getString("id");
			String name=data.getString("place_name");
			String distance = data.getString("distance");
			Facility subway = new Facility(id, name, distance);
			list.add(subway);
		}
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		return list;
	}

    @Override
    public HouseInfoDto getHouseInfo(int aptCode) throws Exception {
        HouseInfoDto house = houseMapMapper.getHouseInfo(aptCode);
        String recentPrice = houseMapMapper.getRecentPrice(house.getAptCode());
        house.setRecentPrice(recentPrice);
        house.setHouseDeals(houseMapMapper.getHouseDeal(aptCode));
        return house;
    }

    @Override
    public DongCodeDto getDongCode(String dongCode) throws Exception {
        return houseMapMapper.getDongCode(dongCode);
    }

    @Override
    public List<HouseInfoDto> getHouseInfoByName(String name) throws Exception {
        List<HouseInfoDto> houseInfoList = houseMapMapper.getHouseInfoByName(name);
        for(HouseInfoDto houseInfo : houseInfoList){
            String recentPrice = houseMapMapper.getRecentPrice(houseInfo.getAptCode());
            houseInfo.setRecentPrice(recentPrice);
            houseInfo.setHouseDeals(houseMapMapper.getHouseDeal(houseInfo.getAptCode()));
        }
        return houseInfoList;
    }

    @Override
    public BaseAddress getAddressByDongCode(String dongCode) throws Exception {
        return houseMapMapper.getAddressByDongCode(dongCode);
    }


}
