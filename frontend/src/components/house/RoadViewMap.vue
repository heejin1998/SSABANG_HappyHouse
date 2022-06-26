<template>
    <div>
        <div id="roadview" style="width: 400px; height: 400px"></div>
    </div>
</template>

<script>
/* global kakao */
export default {
    name: "RoadViewMap",
    props:["house"],
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
            let roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
            let roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            let roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

            let position = new kakao.maps.LatLng(this.house.lat, this.house.lng);

            // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
            roadviewClient.getNearestPanoId(position, 100, function (panoId) {
                roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
        },
    },
};
</script>

<style></style>
