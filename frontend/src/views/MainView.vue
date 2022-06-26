<template>
    <div class="mainPage">
        <main-banner></main-banner>
        <v-container>
            <info-item-container></info-item-container>
            <v-container>
                <home-intro></home-intro>
                <develop-intro></develop-intro>
            </v-container>
        </v-container>
        <footer-bar></footer-bar>
    </div>
</template>

<script>
import MainBanner from "@/components/main/MainBanner.vue";
import InfoItemContainer from "@/components/main/InfoItemContainer.vue";
import HomeIntro from "@/components/home/HomeIntro.vue";
import DevelopIntro from "@/components/home/DevelopIntro.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
    components: { MainBanner, InfoItemContainer, HomeIntro, DevelopIntro, FooterBar },
    name: "MainPage",
};
</script>

<style>
.mainPage {
    background-color: #fafafa;
}
</style>
