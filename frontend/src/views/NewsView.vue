<template>
    <div>
        <bread-crumb title="뉴스"></bread-crumb>
        <div class="content-bar">
            <news-list></news-list>
        </div>
        <footer-bar></footer-bar>
    </div>
</template>

<script>
import NewsList from "@/components/news/NewsList.vue";
import BreadCrumb from "@/components/layout/BreadCrumb.vue";
import FooterBar from '@/components/layout/FooterBar.vue';
export default {
    name: "NewsView",
    components: { NewsList, BreadCrumb, FooterBar },
};
</script>

<style></style>
