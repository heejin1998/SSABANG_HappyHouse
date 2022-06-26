<template>
  <v-card class="center">
    <v-card-title class="justify-center">
      <div class="header">
        <h1>내 정보</h1>
      </div>
    </v-card-title>
    <v-row>
      <v-col cols="2"></v-col>
      <v-col cols="2">이름</v-col>
      <v-col cols="4">{{ userInfo.nickname }}</v-col>
    </v-row>
    <v-row>
      <v-col cols="2"></v-col>
      <v-col cols="2">이메일</v-col>
      <v-col cols="4">{{ userInfo.email }}</v-col>
    </v-row>
    <v-row>
      <v-col cols="2"></v-col>
      <v-col cols="2">닉네임</v-col>
      <v-col cols="4">{{ userInfo.username }}</v-col>
    </v-row>
    <v-row>
      <v-col cols="2"></v-col>
      <v-col cols="2">주소</v-col>
      <v-col cols="4">{{ userInfo.address }}</v-col>
    </v-row>
    <v-row>
      <v-col cols="2"></v-col>
      <v-col cols="2">가입일</v-col>
      <v-col cols="4">{{ userInfo.regdate }}</v-col>
    </v-row>
    <v-row>
      <v-col cols="3"></v-col>
      <v-col cols="3">
        <v-btn class="button" to="/user/modify" outlined color="deep-orange"
          >정보 수정</v-btn
        ></v-col
      >
      <v-col cols="3">
        <v-btn
          class="button outlined"
          @click="deleteUser"
          outlined
          color="deep-orange"
          >회원 탈퇴</v-btn
        ></v-col
      >
    </v-row>

    <!-- <v-card-text class="text-center">
      <div class="center-content">
        <h3>이름</h3>
        
      </div>
    </v-card-text> -->
    <v-spacer></v-spacer>

    <!-- <div class="kakao-start">
      v-btn
    </div> -->
  </v-card>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { deleteUser } from "@/api/user.js";

const memberStore = "memberStore";
export default {
  name: "MemberMyPage",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_IS_USER",
    ]),
    deleteUser() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        deleteUser(this.userInfo.kid, (response) => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          this.SET_IS_USER(false);
          console.log(response.data);
          this.$router.push("/");
        });
      }
    },
  },
};
</script>

<style scoped>
.header {
  margin-top: 50px;
  margin-bottom: 50px;
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
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  margin-top: 10%;
  margin-bottom: 10%;
}

.button {
  margin-top: 20px;
  margin-bottom: 30px;
}
</style>
