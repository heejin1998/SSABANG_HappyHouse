<template>
    <div>
        <div class="d-flex flex-row justify-center align-top">
            <div class="select-box">
                <v-select v-model="sidoCode" :items="sidos" class="select" @change="sidoList" dense outlined label="시도" solo></v-select>
            </div>
            <div class="select-box">
                <v-select v-model="gugunCode" :items="guguns" class="select" @change="gugunList" dense outlined label="구군" :elevation="0" solo></v-select>
            </div>
            <div class="select-box">
                <v-select v-model="dongCode" :items="dongs" class="select" @change="dongList" dense outlined label="동" :elevation="0" solo></v-select>
            </div>
        </div>
    </div>
</template>

<script>
/* global kakao */
import { mapActions, mapMutations, mapState } from "vuex";
import { addressByDongCode } from "@/api/house";
const HouseStore = "houseStore";
const MapStore = "mapStore";
export default {
    name: "HouseGeoSelect",
    data() {
        return {
            sidoCode: null,
            gugunCode: null,
            dongCode: null,
        };
    },
    computed: {
        ...mapState(HouseStore, ["sidos", "guguns", "dongs", "houses"]),
        ...mapState(MapStore, ["map"]),
    },
    methods: {
        ...mapActions(HouseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
        ...mapMutations(HouseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
        sidoList() {
            this.CLEAR_GUGUN_LIST();
            this.CLEAR_DONG_LIST();
            this.gugunCode = null;
            this.dongCode = null;
            if (this.sidoCode) this.getGugun(this.sidoCode);
        },
        gugunList() {
            this.CLEAR_DONG_LIST();
            this.dongCode = null;
            if (this.gugunCode) this.getDong(this.gugunCode);
        },
        dongList() {
            addressByDongCode(
                this.dongCode,
                (response) => {
                    let baseAddress = response.data;
                    let moveLatLon = new kakao.maps.LatLng(baseAddress.lat, baseAddress.lng);
                    this.map.panTo(moveLatLon);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
    created() {
        this.CLEAR_SIDO_LIST();
        this.getSido();
    },
};
</script>

<style>
.select-box {
    width: 138px;
    margin: 0px 10px;
}

.select .v-input__slot {
    box-shadow: none !important;
}

.select-box > .select fieldset {
    background: white;
}
</style>
