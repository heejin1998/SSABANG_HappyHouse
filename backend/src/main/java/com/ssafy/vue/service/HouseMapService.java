package com.ssafy.vue.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.vue.dto.*;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptLatLng(double swLat, double swLng, double neLat, double neLng) throws Exception;
	List<HouseInfoDto> getAptLatLngWithDeals(double swLat, double swLng, double neLat, double neLng) throws Exception;
	List<Facility> getDistance(String type, int aptCode) throws IOException;
	HouseInfoDto getHouseInfo(int aptCode) throws Exception;
	DongCodeDto getDongCode(String dongCode) throws Exception;
	List<HouseInfoDto> getHouseInfoByName(String name) throws Exception;
	BaseAddress getAddressByDongCode(String dongCode) throws Exception;
}
