<template>
    <div class="map-wrap">
        <div :id="mapId" style="width: 350px; height: 350px"></div>
    </div>
</template>

<script>
/*global kakao*/
export default {
    name: "StaticMap",
    props: ["aptCode", "house"],
    data() {
        return {
            staticMap: null,
            mapId: "static-map" + this.aptCode,
        };
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
    methods: {
        initMap() {
            // 이미지 지도에서 마커가 표시될 위치입니다
            let markerPosition = new kakao.maps.LatLng(this.house.lat, this.house.lng);

            // 이미지 지도에 표시할 마커입니다
            // 이미지 지도에 표시할 마커는 Object 형태입니다
            let marker = {
                position: markerPosition,
            };

            let staticMapContainer = document.getElementById(this.mapId); // 이미지 지도를 표시할 div
            let staticMapOption = {
                center: new kakao.maps.LatLng(this.house.lat, this.house.lng), // 이미지 지도의 중심좌표
                level: 3, // 이미지 지도의 확대 레벨
                marker: marker,
            };

            // 이미지 지도를 표시할 div와 옵션으로 이미지 지도를 생성합니다
            this.staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
        },
    },
};
</script>

<style>
#static-map {
    position: relative;
    top: 0;
    left: 0;
    width: 600px;
    height: 300px;
}
.map-wrap {
    flex: 1 1 0%;
    position: relative;
    overflow: hidden;
}
</style>
