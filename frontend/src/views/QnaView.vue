<template>
    <div>
        <bread-crumb title="Q & A"></bread-crumb>
        <v-container fluid class="color content-bar">
            <v-container class="justify-center align-center"><router-view></router-view></v-container>
        </v-container>
        <footer-bar></footer-bar>
    </div>
</template>

<script>
import BreadCrumb from "@/components/layout/BreadCrumb.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
    name: "QnaView",
    components: { BreadCrumb, FooterBar },
};
</script>

<style>
.color {
    background-color: #fafafa;
}
</style>
