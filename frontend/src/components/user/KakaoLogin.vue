<template>
  <div style="background-color: #fafafa">
    <v-card class="center" v-if="complete">
      <v-card-title class="justify-center">
        <div class="header">
          <img src="@/assets/house.png" />
          <h1>싸방</h1>
        </div>
      </v-card-title>

      <v-card-text class="text-center">
        <div class="center-content">
          <h2>환영합니다. {{ user.nickname }}({{ user.email }})님.</h2>
          <h2>원활한 사이트 이용을 위해 닉네임을 등록해주세요.</h2>
        </div>
      </v-card-text>
      <v-spacer></v-spacer>
      <v-form class="pa-8" ref="form" v-model="valid" lazy-validation>
        아이디(닉네임)
        <v-text-field
          v-model="user.username"
          :counter="50"
          :rules="titleRules"
          label="id(nickname)"
          required
        ></v-text-field>

        <v-card-actions class="justify-end">
          <v-btn
            :disabled="!valid"
            outlined
            color="deep-orange"
            class="mr-4"
            @click="register"
          >
            작성
          </v-btn>
        </v-card-actions>
      </v-form>

      <!-- <div class="kakao-start">
      v-btn
    </div> -->
    </v-card>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { registerUser, getToken, isUser, getUser } from "@/api/user";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";
export default {
  name: "KakaoLogin",
  created() {
    console.log("create");
    this.create();
  },

  data: () => ({
    valid: true,
    complete: false,
    code: "",
    user: {
      kid: "", // 카카오 api가 반환하는 id
      nickname: "", // 실제 이름
      username: "", //다른 사람들한테 보여지는 닉네임
      email: "",
      address: "",
      regdate: "",
    },
    titleRules: [
      (v) => !!v || "Title is required",
      (v) => (v && v.length <= 50) || "Title must be less than 50 characters",
    ],
    contentRules: [(v) => !!v || "Content is required"],
  }),
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isRegUser"]),
  },

  methods: {
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_IS_USER",
    ]),
    async create() {
      this.code = this.$route.query.code;
      console.log("create - code: ", this.code);
      let num = await this.getToken();
      console.log("getToken await 함수 - ", num);
      if (num == 1 || num == -1) {
        // this.$router.go(this.$router.currentRoute);
        this.$router.push("/");
      }
    },
    async getToken() {
      await getToken(this.code, (response) => {
        this.user.kid = response.data.kid;
        this.user.nickname = response.data.nickname;
        this.user.email = response.data.email;
        console.log("getToken - nickname: ", response.data);

        if (this.user.kid == undefined) {
          alert("올바르지 못 한 접근입니다.");
          // this.$router.push("/");
          return -1;
        }
      });
      await this.SET_USER_INFO(this.user);
      console.log("getToken - kid: ", this.user.kid);
      await this.isUser(this.user.kid);
      console.log("isRegUser: ", this.isRegUser);

      if (this.isRegUser) {
        // 이미 가입한 회원일 경우, userInfo에 저장하고 메인페이지로 redirect
        await this.SET_IS_LOGIN(true);
        await getUser(this.user.kid, (response) => {
          console.log("getToken - getUser 실행", response.data);
          this.user = response.data;
        });
        console.log("전: ", this.userInfo);
        console.log("바꿀 정보: ", this.user);
        await this.SET_USER_INFO(this.user);
        console.log("후: ", this.userInfo);
        console.log("kid: " + this.userInfo.kid + ", 이미 가입함");
        console.log("이미가입한 자의 정보: ", this.user);
        // sessionStorage.setItem("isLogin", true);
        return 1;
        // await this.moveMain();
      } else {
        //새로 가입한 회원일 경우
        this.complete = true;
        return 0;
      }
    },
    moveMain() {
      this.$router.push("/");
    },
    async isUser(kid) {
      console.log("isUser 실행");
      await isUser(kid, (response) => {
        if (response.data === "fail") {
          this.SET_IS_USER(true);
        }
      });
    },
    async register() {
      await registerUser(
        {
          kid: this.user.kid,
          nickname: this.user.nickname,
          username: this.user.username,
          email: this.user.email,
          address: this.user.address,
        },
        (response) => {
          let msg = "등록 과정 중 문제가 생겼습니다.";
          if (response.data == "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      await getUser(this.user.kid, (response) => {
        console.log("getToken - getUser 실행", response.data);
        this.user = response.data;
      });
      await this.SET_IS_LOGIN(true);
      await this.SET_USER_INFO(this.user);
      console.log("신규 가입자 정보 - ", this.user);
      console.log("신규 가입자 저장된 정보 - ", this.userInfo);
      // this.$router.push("/");
      console.log(this.isLogin);
      console.log(this.userInfo.kid);
      this.$router.push("/");
    },

    reset() {
      this.$refs.form.reset();
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
