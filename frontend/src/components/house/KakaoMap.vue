<template>
    <div class="map-wrap">
        <div id="map"></div>
        <div id="custom_typecontrol">
            <house-filter></house-filter>
        </div>
        <div id="custom-select">
            <house-geo-select></house-geo-select>
        </div>
    </div>
</template>

<script>
/* global kakao */
import { mapActions, mapMutations, mapState } from "vuex";
import HouseFilter from "./HouseFilter.vue";
import HouseGeoSelect from "./HouseGeoSelect.vue";
const HouseStore = "houseStore";
const MapStore = "mapStore";

export default {
    components: { HouseFilter, HouseGeoSelect },
    name: "KakaoMap",
    data() {
        return {
            markers: [],
        };
    },
    computed: {
        ...mapState(HouseStore, ["houses", "house"]),
        ...mapState(MapStore, ["map"]),
    },
    methods: {
        ...mapActions(HouseStore, ["getHouseListGeo", "getHouseListGeoWithDeals"]),
        ...mapMutations(MapStore, ["SET_MAP", "SET_MARKERS"]),
        ...mapActions(MapStore, ["addMarkers", "removeMarkers", "updateMarker"]),
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(37.5743822, 126.9688505, 16),
                level: 5,
            };
            this.SET_MAP(new kakao.maps.Map(container, options));
            this.tileLoaded();
        },
        // 타일 영역 변경 이벤트 리스너
        tileLoaded() {
            kakao.maps.event.addListener(this.map, "tilesloaded", () => {
                console.log("tilesloaded lintener");
                if (this.map.getLevel() > 5) this.getHouseListGeo();
                else this.getHouseListGeoWithDeals();
            });
        },
        addClusterer() {
            if (this.map.getLevel() >= 5) {
                // 마커 클러스터러를 생성합니다
                let clusterer = new kakao.maps.MarkerClusterer({
                    map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
                    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
                    minLevel: 7, // 클러스터 할 최소 지도 레벨
                });

                let markers = [];
                for (const house of this.houses) {
                    markers.push(
                        new kakao.maps.Marker({
                            position: new kakao.maps.LatLng(house.lat, house.lng),
                        })
                    );
                }

                clusterer.addMarkers(markers);
            }
        },
    },
    mounted() {
        if (!window.kakao || !window.kakao.maps) {
            const script = document.createElement("script");
            script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=eebc0b21716f727e505cd2f73981eca6&libraries=services,clusterer,drawing`;
            script.onload = () => kakao.maps.load(this.initMap);
            console.log(script.src);
            document.head.appendChild(script);
        } else {
            this.initMap();
        }
    },
    watch: {
        houses() {
            this.removeMarkers();
            this.addMarkers(this.houses);
            if (this.house !== null) this.updateMarker({ house: this.house, imgSrc: require("/src/assets/town_active.png") });
        },
    },
};
</script>

<style scope>
#map {
    position: relative;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
.map-wrap {
    flex: 1 1 0%;
    position: relative;
    overflow: hidden;
}
#custom_typecontrol {
    position: absolute;
    top: 10px;
    left: 10px;
    overflow: hidden;
    margin: 0;
    padding: 0;
    z-index: 1;
    font-size: 12px;
}
#custom-select{
    position: absolute;
    top: 10px;
    right: 10px;
    overflow: hidden;
    margin: 0;
    padding: 0;
    z-index: 1;
}
</style>
