<template>
    <v-app class="d-flex flex-column">
        <nav-bar></nav-bar>
        <router-view></router-view>
    </v-app>
</template>

<script>
import NavBar from "@/components/layout/HeaderNavBar.vue";
export default {
    name: "App",
    components: {
        NavBar,
    },
};
</script>
<style>
.content-bar {
    min-height: 800px;
    background-color: #fafafa;
}
</style>
