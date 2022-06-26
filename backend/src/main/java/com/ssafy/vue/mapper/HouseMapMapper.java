package com.ssafy.vue.mapper;

import com.ssafy.vue.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface HouseMapMapper {
    List<SidoGugunCodeDto> getSido() throws SQLException;
    List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
    List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
    List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
    List<HouseInfoDto> getAptLatLng(double swLat, double swLng, double neLat, double neLng);
    List<HouseDealDto> getHouseDeal(int aptCode);
    String getRecentPrice(int aptCode);
	HouseInfoDto getHouseInfo(int aptCode);
	DongCodeDto getDongCode(String dongCode);
    List<HouseInfoDto> getHouseInfoByName(String name);
    BaseAddress getAddressByDongCode(String dongCode);
}
