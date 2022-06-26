<template>
    <v-container fluid class="container-banner d-flex flex-column justify-center">
        <div class="banner d-flex flex-column justify-center align-center">
            <div>Happy House</div>
        </div>
        <div class="d-flex flex-row justify-center align-top mt-10">
            <v-btn to="/house/map" depressed class="ml-2" color="orange darken-2" dark style="height: 40px">
                Try It Out <v-icon large dark>mdi-magnify</v-icon>
            </v-btn>
        </div>
    </v-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
    name: "MainBanner",
    data() {
        return {
            sidoCode: null,
            gugunCode: null,
            dongCode: null,
        };
    },
    computed: {
        ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    },
    methods: {
        ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
        ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
        gugunList() {
            this.CLEAR_GUGUN_LIST();
            this.CLEAR_DONG_LIST();
            this.gugunCode = null;
            this.dongCode = null;
            if (this.sidoCode) this.getGugun(this.sidoCode);
        },
        dongList(){
            this.CLEAR_DONG_LIST();
            this.dongCode = null;
            if(this.gugunCode) this.getDong(this.gugunCode);
        }
    },
    created() {
        this.CLEAR_SIDO_LIST();
        this.getSido();
    },
};
</script>

<style>
.container-banner {
    height: 420px;
    text-align: center;
    position: relative;
    z-index: 1;
}

.container-banner::after {
    width: 100%;
    height: 100%;
    position: absolute;
    background-image: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)), url("/src/assets/happy_house.jpg");
    background-repeat: no-repeat;
    background-size: 100%;
    background-position: 50% 32%;
    content: "";
    top: 0;
    left: 0;
    z-index: -1;
}

.banner > div {
    color: #fff;
}

.banner > div {
    font-size: 48px;
    line-height: 2;
    border-bottom: 1px solid #fff;
    width: 1216px;
}
</style>
