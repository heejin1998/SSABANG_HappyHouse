<template>
    <v-container fluid class="color">
        <v-container class="d-flex flex-column justify-center align-center">
            <news-item class="my-5" v-for="(news, index) in newsList" :key="index" :news="news"></news-item>
        </v-container>
    </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import NewsItem from "./NewsItem.vue";

const newsStore = "newsStore";

export default {
    components: { NewsItem },
    name: "NewsList",
    computed: {
        ...mapState(newsStore, ["newsList"]),
    },
    methods: {
        ...mapActions(newsStore, ["getNews"]),
    },
    created() {
        this.getNews();
    },
};
</script>

<style>
.color {
    background-color: #fafafa;
}
</style>
