<template>
  <v-container>
    <v-row>
      <v-col><h3>글 상세 조회</h3> </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <v-card class="mx-auto mt=10" elevation="2">
          <v-card-title>{{ notice.title }}</v-card-title>
          <v-row>
            <v-col>
              <v-card-subtitle class="pa-0 ma-7"
                >작성자 : {{ notice.username }}</v-card-subtitle
              ></v-col
            >
            <v-spacer></v-spacer>
            <v-col
              ><v-card-subtitle class="pa-0 ma-3"
                >작성일 : {{ notice.regtime }}</v-card-subtitle
              ></v-col
            >
            <v-col
              ><v-card-subtitle class="pa-0 ma-3"
                >조회수 : {{ notice.hit }}</v-card-subtitle
              ></v-col
            >
          </v-row>
          <v-row class="pb-10 ml-10">
            <v-card-text class="ma-2 pa-2"
              ><div style="white-space: pre">
                {{ notice.content }}
              </div></v-card-text
            ></v-row
          >
        </v-card>
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col cols="8"></v-col>
      <v-col>
        <v-btn @click="moveList" outlined color="deep-orange">목록</v-btn>
      </v-col>
      <v-col>
        <v-btn @click="moveModify" outlined color="deep-orange">글 수정</v-btn>
      </v-col>
      <v-col>
        <v-btn @click="deleteNotice" outlined color="deep-orange"
          >글 삭제</v-btn
        >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { deleteNotice } from "@/api/notice";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeDetail",
  data() {
    return {
      nid: this.$route.params.nid,
      item: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(noticeStore, ["notice"]),
  },
  created() {
    this.create(this.nid);
    // getNotice(
    //   this.$route.params.nid,
    //   (response) => {
    //     this.item = response.data;
    //   },
    //   (error) => {
    //     console.log("get notice 때 에러!!", error);
    //   }
    // );
  },
  methods: {
    ...mapActions(noticeStore, ["getNotice", "deleteNotice"]),
    create(nid) {
      this.getNotice(nid);
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
    moveModify() {
      if (this.userInfo.username === "admin") {
        this.$router.replace({
          name: "noticeModify",
          // params: { nid: this.nid, notice: this.item },
          nid: this.nid,
        });
      } else {
        alert("접근 권한이 없습니다.");
      }
    },
    deleteNotice() {
      if (this.userInfo.username === "admin") {
        if (confirm("삭제하시겠습니까?")) {
          console.log("delete nid: ", this.nid);
          // this.deleteNotice(this.nid, this.notice);
          deleteNotice(this.nid, () => {
            this.$router.push({ name: "noticeList" });
          });
        }
      } else {
        alert("접근 권한이 없습니다.");
      }
    },
  },
};
</script>

<style></style>
