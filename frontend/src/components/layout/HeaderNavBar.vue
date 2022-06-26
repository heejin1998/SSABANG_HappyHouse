<template>
    <div>
        <nav>
            <v-toolbar flat app>
                <!--툴바 타이블 -->
                <v-toolbar-title>
                    <router-link to="/" tag="span" style="cursor: pointer">
                        <img src="@/assets/house.png" class="d-inline-block align-middle" width="40px" alt="house" />
                        <p class="logo-title">{{ appTitle }}</p></router-link
                    >
                </v-toolbar-title>

                <v-spacer></v-spacer>
                <v-btn class="mx-2" text color="black" plain v-for="item in menuItems" :key="item.icon" :title="item.title" :to="item.path" @mousedown="checkVal(item)">
                    <div class="text-subtitle-1 btn-menu">
                        {{ item.title }}
                    </div>
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn v-if="userInfo" to="/user/mypage" outlined color="deep-orange " class="ma-3">
                    <span> {{ userInfo.nickname }}({{ userInfo.username }})님</span>
                </v-btn>

                <!-- <div class="text-center"> -->
                <!-- <v-menu open-on-hover offset-y :close-on-content-click="false">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              outlined
              color="deep-orange"
              dark
              v-bind="attrs"
              v-on="on"
              v-if="userInfo"
              class="ma-2"
            >
              {{ userInfo.username }}({{ userInfo.nickname }})님
            </v-btn>
          </template>

          <v-list>
            <v-list-item
              v-for="(item, index) in items"
              :key="index"
              :to="item.to"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu> -->
                <!-- </div> -->

                <v-btn v-if="userInfo" @click="onClickLogout" outlined color="deep-orange">
                    <span>로그아웃</span>
                </v-btn>

                <!-- <v-btn v-else to="/user/signin">
        <span>로그인</span>
      </v-btn> -->
                <v-btn v-else to="/user/signin" class="ma-2" outlined color="deep-orange"><span>로그인</span> </v-btn>
            </v-toolbar>
        </nav>
    </div>

    <!-- <v-btn v-else to="/user/signin">
                    <span>로그인</span>
                </v-btn>
            </v-toolbar>
        </nav>
   
>>>>>>> b29c212511baf843f20c31c6ce4e05e72f87640c -->
</template>

<script>
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";
export default {
    name: "HeaderNavBar",
    created() {
        this.create();
    },

    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
    },
    methods: {
        ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
        create() {
            console.log("create");
            console.log("create - userInfo : ", this.userInfo);
        },
        onClickLogout() {
            alert("로그아웃되었습니다.");
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            if (this.$route.path != "/") this.$router.push({ name: "main" });
        },
        colorChange() {
            console.log("color change");
            this.classColor = "#ffa726";
        },
        movePage(path) {
            console.log("move!!");
            this.$router.push(path);
        },
        checkVal(item) {
            console.log(item);
            if (item.title === "관심 매물") {
                if (this.userInfo) {
                    console.log("접근 가능");
                } else {
                    alert("로그인이 필요합니다.");
                    this.$router.push("/user/signin");
                }
            }
        },
    },
    data() {
        return {
            appTitle: "싸방",
            sidebar: false,
            classColor: "black",
            items: [
                { title: "마이페이지", to: "/user/mypage" },
                { title: "관심 매물", to: "/user/favor" },
            ],
            menuItems: [
                // { title: "Home", path: "/home", icon: "home" },
                { title: "아파트 매물", path: "/house/map", icon: "house" },
                { title: "뉴스", path: "/news", icon: "news" },
                { title: "공지사항", path: "/notice", icon: "notice" },
                { title: "Q&A", path: "/qna", icon: "qna" },
                { title: "관심 매물", path: "/user/favor", icon: "favor" },
            ],
        };
    },
};
</script>
<style scoped>
.logo-title {
    margin-left: 10px;
    padding-bottom: 10px;
    margin-bottom: 20px;
    display: inline;
    font-family: "Jua", sans-serif;
    font-size: 40px;
}

.btn-menu {
    color: black;
    font-weight: bold;
}
/* .navbar-btn {
  color: #fafafa;
} */
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
</style>
