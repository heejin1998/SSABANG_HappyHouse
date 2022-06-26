<template>
  <div class="homePage">
    <home-intro></home-intro>
    <develop-intro></develop-intro>
  </div>
</template>

<script>
import HomeIntro from "@/components/home/HomeIntro.vue";
import DevelopIntro from "@/components/home/DevelopIntro.vue";

export default {
  name: "HomeView",
  components: { HomeIntro, DevelopIntro },
};
</script>

<style>
.homePage {
  background-color: #fafafa;
}
</style>
