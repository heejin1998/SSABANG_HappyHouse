<template>
  <div>
    <bread-crumb title="회원"></bread-crumb>
    <v-container fluid class="content-bar">
      <v-container>
        <router-view></router-view>
      </v-container>
    </v-container>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import BreadCrumb from "@/components/layout/BreadCrumb.vue";
import FooterBar from '@/components/layout/FooterBar.vue';
export default {
  name: "MemberView",
  components: { BreadCrumb , FooterBar},
};
</script>

<style>
.color {
  background-color: #fafafa;
}
</style>
