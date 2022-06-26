<template>
    <div>
        <h2 class="my-3">{{ userInfo.nickname }}({{ userInfo.username }})님의 관심 매물</h2>
        <v-divider class="my-8"></v-divider>

        <v-row>
            <v-col cols="12" sm="3" md="4" v-for="(favor) in favors" :key="favor.aptCode">
                <house-favor :aptCode="favor.aptCode"></house-favor>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HouseFavor from "../house/HouseFavor.vue";

const memberStore = "memberStore";
export default {
    components: { HouseFavor },
    name: "memberFavor",
    head() {
        return {
            script: [{ src: "//developers.kakao.com/sdk/js/kakao.min.js" }],
        };
    },
    data() {
        return {
            headers: [{ text: "aptCode", align: "center", sortable: false, value: "aptCode" }],
            iconColor: "pink",
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo", "favors"]),
    },
    created() {
        this.create(this.userInfo.kid);
        console.log("created - ", this.userInfo.kid);
    },
    methods: {
        ...mapActions(memberStore, ["listFavor"]),
        create(kid) {
            console.log("method create - ", kid);
            this.listFavor(kid);
        },
    },
};
</script>

<style>
</style>
