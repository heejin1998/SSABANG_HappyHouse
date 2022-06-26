<template>
    <v-card max-width="1300" min-width="1300" hover @click.native="openNews">
        <v-card-title v-html="news.title"> </v-card-title>
        <v-card-subtitle>
            {{ news.pubDate }}
        </v-card-subtitle>
        <v-card-text v-html="news.description"> </v-card-text>
    </v-card>
</template>

<script>
export default {
    name: "NewsItem",
    props: ["news"],
    data() {
        return {
            isRead: false,
        };
    },
    methods: {
        openNews() {
            window.open(this.news.link);
            this.isRead = true;
        },
    },
};
</script>

<style>
.v-card--link:before {
    background: none;
}
</style>
