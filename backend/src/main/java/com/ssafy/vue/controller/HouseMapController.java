package com.ssafy.vue.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.vue.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {

    private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

    @Autowired
    private HouseMapService haHouseMapService;

    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
        return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
        return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
    }

    @GetMapping("/dong")
    public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
    }

    @GetMapping("/apt")
    public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
    }

    @GetMapping("/aptgeo")
    public List<HouseInfoDto> getAptLatLng(@RequestParam("swLat") double swLat, @RequestParam("swLng") double swLng, @RequestParam("neLat") double neLat, @RequestParam("neLng") double neLng) throws Exception {
        return haHouseMapService.getAptLatLng(swLat, swLng, neLat, neLng);
    }

    @GetMapping("/aptgeo/deals")
    public List<HouseInfoDto> getAptLatLngWithDeals(@RequestParam("swLat") double swLat, @RequestParam("swLng") double swLng, @RequestParam("neLat") double neLat, @RequestParam("neLng") double neLng) throws Exception {
        return haHouseMapService.getAptLatLngWithDeals(swLat, swLng, neLat, neLng);
    }

    @GetMapping("/distance/{type}/{aptCode}") // type - 지하철(SW8), 학교(SC4)
	public ResponseEntity<List<Facility>> getDistance(@PathVariable String type, @PathVariable int aptCode) throws IOException{
		return new ResponseEntity<List<Facility>>(haHouseMapService.getDistance(type, aptCode), HttpStatus.OK);
	}

	@GetMapping("/apt/{aptCode}")
	public HouseInfoDto getHouseInfo(@PathVariable int aptCode) throws Exception {
        return haHouseMapService.getHouseInfo(aptCode);
    }

    @GetMapping("/dong/{dongCode}")
    public DongCodeDto getDongCode(@PathVariable String dongCode) throws Exception{
        return haHouseMapService.getDongCode(dongCode);
    }

    @GetMapping("/apt/name/{name}")
    public List<HouseInfoDto> getHouseInfoByName(@PathVariable String name) throws Exception {
        return haHouseMapService.getHouseInfoByName(name);
    }

    @GetMapping("/geo/{dongCode}")
    public BaseAddress getAddressByDongCode(@PathVariable String dongCode) throws Exception {
        return haHouseMapService.getAddressByDongCode(dongCode);
    }
}
