<template>
  <v-card class="center">
    <v-card-title class="justify-center">
      <div class="header">
        <h1>회원 정보 수정</h1>
      </div>
    </v-card-title>
    <v-form class="pa-8" ref="form" v-model="valid" lazy-validation>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2">이름</v-col>
        <v-text-field
          v-model="user.nickname"
          :counter="50"
          :rules="titleRules"
          disabled
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2">이메일</v-col>
        <v-text-field
          v-model="user.email"
          :counter="50"
          :rules="titleRules"
          disabled
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2">닉네임</v-col>
        <v-text-field
          v-model="user.username"
          :counter="50"
          :rules="titleRules"
          required
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2">주소</v-col>
        <v-text-field
          v-model="user.address"
          :counter="50"
          :rules="titleRules"
          required
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2">가입일</v-col>
        <v-text-field
          v-model="user.regdate"
          :counter="50"
          :rules="titleRules"
          disabled
        ></v-text-field>
        <v-col cols="1"></v-col>
      </v-row>
      <v-row>
        <v-col cols="4"></v-col>
        <v-col cols="2">
          <v-btn @click="updateUser" class="button" outlined color="deep-orange"
            >정보 수정</v-btn
          ></v-col
        >
      </v-row>
    </v-form>

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
import { updateUser } from "@/api/user.js";

const memberStore = "memberStore";
export default {
  name: "MemberModify",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
    console.log("created - userInfo : ", this.userInfo);
    console.log("created - user : ", this.user);
  },
  data() {
    return {
      user: {
        nickname: "",
        email: "",
        username: "",
        address: "",
        regdate: "",
      },
    };
  },

  methods: {
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
    updateUser() {
      updateUser(this.userInfo.kid, {
        nickname: this.user.nickname,
        email: this.user.email,
        username: this.user.username,
        address: this.user.address,
        regdate: this.user.regdate,
      }),
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        };
      this.SET_USER_INFO(this.user);
      console.log("update - userInfo : ", this.userInfo);
      this.$router.push("/user/mypage");
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
