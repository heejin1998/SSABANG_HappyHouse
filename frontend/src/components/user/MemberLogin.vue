<template>
  <v-card class="center">
    <v-card-title class="justify-center">
      <div class="header">
        <img src="@/assets/house.png" />
        <h1>싸방</h1>
      </div>
    </v-card-title>

    <v-card-text class="text-center">
      <div class="center-content">
        <h2>간편하게 로그인하고</h2>
        <h2>다양한 서비스를 이용하세요.</h2>
      </div>
    </v-card-text>
    <v-spacer></v-spacer>
    <div class="kakao-start">
      <img src="@/assets/kakao_login.png" @click="login()" class="" />
    </div>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "MemberLogin",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    login() {
      const REST_API_KEY = "820050bdaece0724c93dd4bd8274b037";
      const REDIRECT_URI = "http://localhost:8081/klogin";
      window.location.replace(
        `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`
      );
    },
  },
};
</script>

<style scoped>
.header {
  margin-top: 30px;
}
.header img {
  width: 80px;
  height: 80px;
  display: inline-block;
}
.header h1 {
  margin-left: 50px;
  display: inline;
}

.center-content {
  margin-top: 50px;
}

.kakao-start img {
  margin-top: 50px;
  padding-bottom: 50px;
  margin-left: auto;
  margin-right: auto;
  display: block;
  cursor: pointer;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  margin-top: 10%;
  margin-bottom: 10%;
}
</style>
