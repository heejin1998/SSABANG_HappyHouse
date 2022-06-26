<template>
    <div class="content-wrap">
        <div class="content">
            <div class="map">
                <kakao-map></kakao-map>
            </div>
            <div class="hinfo">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
import KakaoMap from "@/components/house/KakaoMap.vue";

export default {
    components: { KakaoMap},
};
</script>

<style scope>
.content-wrap {
    flex: 1 0 auto;
    display: flex;
    flex-direction: column;
    max-height: 100%;
    background-color: white;
}

.content {
    flex: 1 1 0%;
    display: flex;
}

.map {
    flex: 1 1 0%;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    border-right: 1px solid rgb(225, 225, 225);
}

.content-wrap .hinfo {
    flex: 0 0 400px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    position: relative;
    max-height: 100%;
    background-color: white !important;
}
</style>
