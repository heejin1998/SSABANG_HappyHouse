<template>
    <div id="house-filter">
        <div class="pa-3">
            <div class="d-flex">
                <div class="search-input-liner">
                    <div class="input-wrap">
                        <input v-on:keydown.enter.prevent="search" v-model="searchName" type="text" placeholder="아파트명 검색" autocomplete="off" />
                    </div>
                    <div class="btn-search-wrap">
                        <button @click="search" class="btn-search"></button>
                    </div>
                </div>
            </div>
        </div>
        <v-expansion-panels class="pannels" v-model="panel" :disabled="isPanel" accordion>
            <v-expansion-panel key="0">
                <v-expansion-panel-header> {{ panelMsg }} </v-expansion-panel-header>
                <v-expansion-panel-content class="pannel-content pa-0">
                    <div class="d-flex flex-column">
                        <div class="pannel-item">
                            <div class="pannel-title">평균 거래가</div>
                            <div class="pannel-subtitle">
                                가격 {{ recentPrice[0] * this.pivot[this.recentPrice[0]] }}~{{ recentPrice[1] * this.pivot[this.recentPrice[1]] }} 만원
                            </div>
                            <v-range-slider
                                @mouseup="changeEvent"
                                v-model="recentPrice"
                                color="orange darken-3"
                                track-color="orange lighten-3"
                                max="10"
                                step="1"
                            ></v-range-slider>
                        </div>
                        <div class="pannel-item">
                            <div class="pannel-title">건축 년도</div>
                            <div class="pannel-subtitle">{{ buildYear[0] + 1960 }}~{{ buildYear[1] + 1960 }} 년도</div>
                            <v-range-slider
                                @mouseup="changeEvent"
                                v-model="buildYear"
                                color="orange darken-3"
                                min="0"
                                max="65"
                                track-color="orange lighten-3"
                                step="10"
                            ></v-range-slider>
                        </div>
                        <div class="pannel-item">
                            <div class="pannel-title">아파트 층수</div>
                            <div class="pannel-subtitle">{{ floor[0] }}~{{ floor[1] }} 층</div>
                            <v-range-slider
                                @mouseup="changeEvent"
                                v-model="floor"
                                min="1"
                                max="67"
                                color="orange darken-3"
                                track-color="orange lighten-3"
                                step="10"
                            ></v-range-slider>
                        </div>
                    </div>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
    </div>
</template>

<script>
/* global kakao */
import { mapActions, mapMutations, mapState } from "vuex";

const MapStore = "mapStore";
const HouseStore = "houseStore";

export default {
    name: "HouseFilter",
    data() {
        return {
            recentPrice: [0, 10],
            buildYear: [0, 65],
            floor: [1, 67],
            pivot: [0, 10, 50, 100, 300, 1000, 5000, 10000, 50000, 150000, 850000],
            searchName: "",
            panel: [],
            isPanel: true,
            panelMsg: "화면을 확대해주세요",
        };
    },
    created() {},
    computed: {
        ...mapState(MapStore, ["map"]),
    },
    methods: {
        ...mapActions(HouseStore, ["setHouseFilter", "getHouseListGeoWithDeals", "getHouseInfoByName"]),
        ...mapMutations(HouseStore, ["SET_FILTER"]),
        changeEvent() {
            let filter = {
                price: [this.recentPrice[0] * this.pivot[this.recentPrice[0]], this.recentPrice[1] * this.pivot[this.recentPrice[1]]],
                year: [this.buildYear[0] + 1960, this.buildYear[1] + 1960],
                floor: [this.floor[0], this.floor[1]],
            };
            this.SET_FILTER(filter);
            if (this.map.getLevel() < 5) this.getHouseListGeoWithDeals();
        },
        search() {
            this.getHouseInfoByName({ aptName: this.searchName });
        },
    },
    watch: {
        map() {
            if (this.map !== null) {
                kakao.maps.event.addListener(this.map, "tilesloaded", () => {
                    if (this.map.getLevel() > 4) {
                        this.panel = [];
                        this.panelMsg = "화면을 확대해주세요";
                        this.isPanel = true;
                    } else {
                        this.panelMsg = "검색 조건을 설정하세요";
                        this.isPanel = false;
                    }
                });
            }
        },
    },
};
</script>

<style scope>
#house-filter {
    border: 1px #a5a5a5 solid;
    background-color: white;
}

#house-filter .pannels {
    font-size: 13px;
}

#house-filter .search-input-liner {
    flex: 1 1 0%;
    border: 1px solid rgb(250, 136, 11);
    border-radius: 4px;
    overflow: hidden;
    height: 30px;
    vertical-align: top;
    box-sizing: border-box;
}

#house-filter .input-wrap {
    float: left;
    width: 285px;
    padding: 0px 10px;
    position: relative;
    overflow: hidden;
}
#house-filter .input-wrap input {
    width: 100%;
    height: 27px;
    border: none;
    appearance: none;
    outline: none;
    box-shadow: none;
    font-size: 13px;
}
#house-filter .btn-search-wrap {
    float: right;
    background-color: rgb(250, 136, 11);
    width: 30px;
    height: 30px;
    overflow: hidden;
}
#house-filter .btn-search {
    width: 30px;
    height: 30px;
    background: url(https://s.zigbang.com/www/images/ic_title_searchbar_30x30_nor_white.png) center center / 100% no-repeat transparent;
}
#house-filter .v-expansion-panel-content__wrap {
    padding: 0;
    flex: 1 1 auto;
    max-width: 100%;
}
#house-filter .pannel-title {
    margin-top: 13px;
    margin-bottom: 3px;
    font-size: 14px;
    color: rgb(34, 34, 34);
}
#house-filter .pannel-subtitle {
    line-height: 30px;
    font-size: 20px;
    color: rgb(250, 136, 11);
    font-weight: bold;
    margin-bottom: 15px;
}
#house-filter .pannel-item {
    border-top: 4px #eeeeee solid;
    padding: 0 24px;
}
</style>
