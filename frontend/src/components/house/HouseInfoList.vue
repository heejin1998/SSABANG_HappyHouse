<template>
    <div>
        <div id="house-length" class="d-flex flex-row justify-left align-center pl-2 text-h6">
            아파트 목록  <span class="item-info">{{houses.length}}</span>개
        </div>
        <v-virtual-scroll id="house-info-list" max-height="83vh" :items="houses" item-height="150">
            <template v-slot:default="{ item }">
                <v-card hover class="mx-auto rounded-0" width="400" :to="path+item.aptCode" max-width="400" outlined  @mouseenter="mouseEnter(item)" @mouseleave="mouseLeave(item)">
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title class="text-h5 mb-1">{{ item.aptName }}</v-list-item-title>
                            <v-list-item-subtitle>동코드 {{ item.dongCode }}</v-list-item-subtitle>
                            
                            <hr class="my-3 hr">
                            <div class="d-flex flex-row">
                                <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                    <div class="item-sub">건축년도</div>
                                    <div class="item-info">{{ item.buildYear }}</div>
                                </div>
                                <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                    <div class="item-sub">최근 거래가</div>
                                    <div class="item-info">{{item.recentPrice}}</div>
                                </div>
                                <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                    <div class="item-sub">지번</div>
                                    <div class="item-info">{{ item.jibun }}</div>
                                </div>
                            </div>
                        </v-list-item-content>
                    </v-list-item>
                </v-card>
            </template>
        </v-virtual-scroll>
    </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const HouseStore = "houseStore";
const MapStore = "mapStore";

export default {
    name: "HouseInfoList",
    data() {
        return {
            path:"/house/item/",
            activeSrc: require("/src/assets/town_active.png"),
            disableSrc: require("/src/assets/town.png"),
        };
    },
    computed: {
        ...mapState(HouseStore, ["houses"]),
    },
    methods: {
        ...mapActions(MapStore, ["updateMarker"]),
        mouseEnter(house) {
            this.updateMarker({ house: house, imgSrc: this.activeSrc });
        },
        mouseLeave(house) {
            this.updateMarker({ house: house, imgSrc: this.disableSrc });
        },
    },
};
</script>

<style>
#house-length{
    background-color: white;
    height: 50px;
    border: thin solid rgba(0, 0, 0, 0.12);
}

#house-info-list hr{
    height: 1px;
    background-color: rgba(0,0,0,0.12);
    color: rgba(0,0,0,0.12);
    border-color:rgba(0,0,0,0.12);
    border: none;
}

#house-info-list {
    scrollbar-width: none;
    background-color: white;
}

#house-info-list::-webkit-scrollbar {
    display: none;
}

.item-info {
    color: rgb(45, 96, 163);
    font-size: 20px;
    font-weight: bold;
    line-height: 26px;
    background-color: white;
}

.item-sub {
    color: rgb(34, 34, 34);
    font-size: 16px;
    line-height: 21px;
}
</style>
