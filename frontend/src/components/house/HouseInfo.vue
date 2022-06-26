<template>
    <div>
        <div id="house-info-header" class="d-flex flex-row justify-space-between align-center pl-2 text-h6">
            <div>
                <v-btn icon @click="back">
                    <v-icon>mdi-arrow-left-thick</v-icon>
                </v-btn>
                <span class="item-info">{{ dongCode.sidoName }} {{ dongCode.gugunName }} {{ dongCode.dongName }}</span>
            </div>
        </div>
        <div id="house-info">
            <div style="width: 400px; height: 400px">
                <road-view-map :house="this.house"></road-view-map>
            </div>
            <v-card class="mx-auto rounded-0 house-info-v-card" width="400" max-width="400" outlined>
                <v-list-item>
                    <v-list-item-content>
                        <div class="d-flex flex-row justify-space-between align-center">
                            <div class="text-h5 mb-1">
                                {{ house.aptName }}
                            </div>
                            <div v-if="userInfo !== null">
                                <v-btn v-if="!isFavor" icon color="gray" @click="activeFavor">
                                    <v-icon>mdi-heart</v-icon>
                                </v-btn>
                                <v-btn v-if="isFavor" icon color="pink" @click="disableFavor">
                                    <v-icon>mdi-heart</v-icon>
                                </v-btn>
                            </div>
                        </div>
                        <v-list-item-subtitle>동코드 {{ house.dongCode }}</v-list-item-subtitle>

                        <hr class="my-3 hr" />
                        <div class="d-flex flex-row">
                            <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                <div class="item-sub">건축년도</div>
                                <div class="item-info">{{ house.buildYear }}</div>
                            </div>
                            <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                <div class="item-sub">최근 거래가</div>
                                <div class="item-info">{{ house.recentPrice }}</div>
                            </div>
                            <div class="flex-grow-1 d-flex flex-column justify-center align-center">
                                <div class="item-sub">지번</div>
                                <div class="item-info">{{ house.jibun }}</div>
                            </div>
                        </div>
                    </v-list-item-content>
                </v-list-item>
            </v-card>
            <v-card class="mx-auto rounded-0 house-info-v-card" width="400" max-width="400" outlined>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>거래 정보</v-list-item-title>

                        <hr class="my-3 hr" />

                        <v-simple-table dense>
                            <thead>
                                <tr>
                                    <th class="text-left">Date</th>
                                    <th class="text-left">Deal</th>
                                    <th class="text-left">Area</th>
                                    <th class="text-left">Floor</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="deal in house.houseDeals" :key="deal.index">
                                    <th>{{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}</th>
                                    <th>{{ deal.dealAmount }}</th>
                                    <th>{{ deal.area }}</th>
                                    <th>{{ deal.floor }}</th>
                                </tr>
                            </tbody>
                        </v-simple-table>
                    </v-list-item-content>
                </v-list-item>
            </v-card>
            <v-card class="mx-auto rounded-0 house-info-v-card" width="400" max-width="400" outlined>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>주변 시설</v-list-item-title>

                        <hr class="my-3 hr" />

                        <v-simple-table dense>
                            <thead>
                                <tr>
                                    <th class="text-left">분류</th>
                                    <th class="text-left">이름</th>
                                    <th class="text-left">거리</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(element, index) in distance" :key="index">
                                    <th>{{ element.category }}</th>
                                    <th>{{ element.name }}</th>
                                    <th>{{ element.distance }}m</th>
                                </tr>
                            </tbody>
                        </v-simple-table>
                    </v-list-item-content>
                </v-list-item>
            </v-card>
        </div>
    </div>
</template>

<script>
/* global kakao */
import { mapActions, mapMutations, mapState } from "vuex";
import { dongCode, distance } from "@/api/house.js";
import { deleteFavor, insertFavor } from "@/api/user.js";
import RoadViewMap from "./RoadViewMap.vue";

const HouseStore = "houseStore";
const MapStore = "mapStore";
const MemberStore = "memberStore";

export default {
    components: { RoadViewMap },
    name: "HouseInfo",
    data() {
        return {
            aptCode: this.$route.params.aptCode,
            dongCode: {},
            disableSrc: require("/src/assets/town.png"),
            activeSrc: require("/src/assets/town_active.png"),
            distance: [],
            isFavor: false,
        };
    },
    created() {
        this.getHouseInfo(this.aptCode);
        if (this.userInfo !== null) this.listFavor(this.userInfo.kid);
        this.getDistance("MT1", "대형마트");
        this.getDistance("SC4", "학교");
        this.getDistance("SW8", "지하철역");
    },
    computed: {
        ...mapState(HouseStore, ["house"]),
        ...mapState(MapStore, ["map"]),
        ...mapState(MemberStore, ["isLogin", "favors", "userInfo"]),
    },
    mounted() {
        console.log(this.favors);
    },
    methods: {
        ...mapMutations(HouseStore, ["SET_HOUSE_BY_APTCODE", "SET_HOUSE_INIT"]),
        ...mapActions(HouseStore, ["getHouseInfo"]),
        ...mapActions(MapStore, ["updateMarker"]),
        ...mapActions(MemberStore, ["listFavor"]),
        back() {
            this.updateMarker({ house: this.house, imgSrc: this.disableSrc });
            this.$router.push("/house/map");
        },
        getDistance(category, name) {
            distance({ category, aptCode: this.aptCode }, (response) => {
                let data = response.data;
                for (const element of data) {
                    element.category = name;
                }
                this.distance.push(...data);
            });
        },
        activeFavor() {
            insertFavor(
                this.userInfo.kid,
                this.house.aptCode,
                () => {
                    this.isFavor = true;
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        disableFavor() {
            deleteFavor(
                this.userInfo.kid,
                this.house.aptCode,
                () => {
                    this.isFavor = false;
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
    destroyed() {
        this.SET_HOUSE_INIT();
    },
    watch: {
        house() {
            dongCode(
                this.house.dongCode,
                (response) => {
                    this.dongCode = response.data;
                },
                (error) => {
                    console.log(error);
                }
            );
            let moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);
            this.map.panTo(moveLatLon);
            this.updateMarker({ house: this.house, imgSrc: this.activeSrc });
        },
        favors() {
            for (const favor of this.favors) {
                if (favor.aptCode == this.house.aptCode) {
                    this.isFavor = true;
                    break;
                }
            }
        },
    },
};
</script>

<style scoped>
#house-info {
    max-height: 82vh;
    overflow: auto;
    scrollbar-width: none;
}

#house-info-header {
    background-color: white;
    height: 50px;
    border: thin solid rgba(0, 0, 0, 0.12);
}

#house-info::-webkit-scrollbar {
    display: none;
}

#house-info hr {
    height: 1px;
    background-color: rgba(0, 0, 0, 0.12);
    color: rgba(0, 0, 0, 0.12);
    border-color: rgba(0, 0, 0, 0.12);
    border: none;
}

#house-info .house-info-v-card {
    border-bottom: 6px rgba(0, 0, 0, 0.12) solid;
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
