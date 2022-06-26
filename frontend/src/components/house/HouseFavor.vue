<template>
    <div id="house-favor">
        <v-card hover class="mx-auto" @mousedown.stop="pushRoute" max-width="400">
            <v-list-item>
                <v-list-item-content>
                    <div  style="width:350px;height:350px;">
                        <static-map :aptCode="this.house.aptCode" :house="this.house"></static-map>
                    </div>
                    <div class="d-flex flex-row justify-space-between align-center">
                        <div class="text-h5 mb-1">
                            {{ house.aptName }}
                        </div>
                        <div class="icon">
                            <v-card-actions class="justify-end">
                                <img src="@/assets/kakao_share.png" @mousedown.stop="kakaoShare()" class="share-icon" />
                                <v-btn icon :color="iconColor" @mousedown.stop="deleteFavor()">
                                    <v-icon>mdi-heart</v-icon>
                                </v-btn>
                            </v-card-actions>
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
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deleteFavor } from "@/api/user";
import { houseInfo } from "@/api/house";
import StaticMap from "@/components/house/StaticMap.vue";

const memberStore = "memberStore";
export default {
    components: { StaticMap },
    name: "HouseFavor",
    props: ["aptCode"],
    head() {
        return {
            script: [{ src: "//developers.kakao.com/sdk/js/kakao.min.js" }],
        };
    },
    data() {
        return {
            headers: [{ text: "aptCode", align: "center", sortable: false, value: "aptCode" }],
            iconColor: "pink",
            house: null,
            path: "/house/item/",
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo", "favors"]),
    },
    created() {
        console.log("created - ", this.userInfo.kid);
        houseInfo(
            this.aptCode,
            (response) => {
                this.house = response.data;
                console.log(this.house);
            },
            (error) => {
                console.log(error);
            }
        );
    },
    methods: {
        ...mapActions(memberStore, ["listFavor"]),
        kakaoShare() {
            let currentUrl = `http://localhost:8081/house/item/${this.aptCode}`;
            window.Kakao.Link.sendDefault({
                objectType: "feed",
                content: {
                    title: "매물 추천",
                    description: "집 정보",
                    imageUrl: "@/assets/happy_house.png",
                    link: {
                        mobileWebUrl: currentUrl,
                        webUrl: currentUrl,
                        androidExecutionParams: "test",
                    },
                },
            });
        },
        deleteFavor() {
            if (confirm("관심 매물을 삭제하시겠습니까?")) {
                console.log("delete 함수 호출");
                deleteFavor(
                    this.userInfo.kid,
                    this.aptCode,
                    (response) => {
                        console.log(response.data);
                        this.$router.go(this.$router.currentRoute);
                    },
                    (error) => {
                        console.log(error);
                    }
                );
            }
        },
        pushRoute() {
            this.$router.push(`/house/item/${this.aptCode}`);
        },
    },
};
</script>

<style>

#house-favor{
    max-width: 371px;
}

#house-favor hr {
    height: 1px;
    background-color: rgba(0, 0, 0, 0.12);
    color: rgba(0, 0, 0, 0.12);
    border-color: rgba(0, 0, 0, 0.12);
    border: none;
}

#house-favor .item-info {
    color: rgb(45, 96, 163);
    font-size: 20px;
    font-weight: bold;
    line-height: 26px;
    background-color: white;
}

#house-favor .item-sub {
    color: rgb(34, 34, 34);
    font-size: 16px;
    line-height: 21px;
}

.share-icon {
    cursor: pointer;
    margin: 10px;
}
.trash-icon {
    width: 40px;
    height: 40px;
    margin: 10px;
}
</style>
