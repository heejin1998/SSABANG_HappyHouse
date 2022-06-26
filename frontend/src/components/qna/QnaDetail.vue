<template>
  <v-container>
    <v-row>
      <v-col><h3>Q&A 상세 조회</h3></v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <v-card elevation="2">
          <v-card-title>{{ qna.title }}</v-card-title>
          <v-row>
            <v-col>
              <v-card-subtitle class="pa-0 ma-7"
                >작성자 : {{ qna.username }}</v-card-subtitle
              ></v-col
            >

            <v-spacer></v-spacer>
            <v-col
              ><v-card-subtitle class="pa-0 ma-3"
                >작성일 : {{ qna.regtime }}</v-card-subtitle
              ></v-col
            >
            <v-col
              ><v-card-subtitle class="pa-0 ma-3"
                >조회수 : {{ qna.hit }}</v-card-subtitle
              ></v-col
            >
          </v-row>
          <v-row class="pb-10 ml-10">
            <v-card-text class="ma-2 pa-2"
              ><div style="white-space: pre">
                {{ qna.content }}
              </div></v-card-text
            ></v-row
          >
        </v-card>
      </v-col>
    </v-row>
    <!--댓글 리스트-->

    <v-row class="mb-1">
      <v-col>
        <v-card class="mx-auto mt=10" elevation="2">
          <v-card-title>댓글</v-card-title>

          <div v-for="(reply, index) in replys" :key="index">
            <v-row>
              <v-col cols="1"></v-col>
              <v-col cols="2">
                <v-card-subtitle>{{ reply.username }} </v-card-subtitle></v-col
              >
              <v-col>
                <v-card-text
                  ><div style="white-space: pre">
                    {{ reply.content }}
                  </div></v-card-text
                ></v-col
              >
              <v-col cols="2"
                ><h5>{{ reply.regtime }}</h5></v-col
              >
            </v-row>
          </div>

          <!--댓글 작성 부분-->
          <v-form class="pa-8" ref="form" lazy-validation>
            <v-textarea
              solo
              v-model="reply.content"
              label="content"
              required
            ></v-textarea>
            <v-card-actions class="justify-end">
              <v-btn
                outlined
                color="deep-orange"
                class="mr-4"
                @click="registerReply"
              >
                작성
              </v-btn>
            </v-card-actions>
          </v-form>
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
        <v-btn @click="deleteQna" outlined color="deep-orange">글 삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { deleteQna, getQnaReply, writeQnaReply } from "@/api/qna";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaDetail",
  data() {
    return {
      qid: this.$route.params.qid,
      item: {},
      replys: [],
      reply: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(qnaStore, ["qna"]),
  },
  created() {
    this.create(this.qid);
    getQnaReply(
      this.$route.params.qid,
      (response) => {
        this.replys = response.data;
        console.log("created - getQnaReply: ", this.replys);
      },
      (error) => {
        console.log("created - get qnaReply error!!", error);
      }
    );
  },
  methods: {
    ...mapActions(qnaStore, ["getQna"]),
    create(qid) {
      this.getQna(qid);
    },
    moveList() {
      this.$router.push({ name: "qnaList" });
    },
    moveModify() {
      console.log(this.qna.username);
      if (this.userInfo) {
        if (this.userInfo.username == this.qna.username) {
          this.$router.replace({
            name: "qnaModify",
            params: { qid: this.qna.qid, qna: this.qna },
          });
        } else {
          alert("접근 권한이 없습니다.");
        }
      } else {
        alert("로그인이 필요합니다.");
        this.$router.push("/user/signin");
      }
    },
    deleteQna() {
      console.log("this.item : ", this.qna.username);
      console.log("store userInfo: ", this.userInfo.username);
      if (this.userInfo) {
        if (this.userInfo.username == this.qna.username) {
          if (confirm("삭제하시겠습니까?")) {
            console.log("delete qid: ", this.qna.qid);
            deleteQna(this.qna.qid, () => {
              this.$router.push({ name: "qnaList" });
            });
          }
        } else {
          alert("접근 권한이 없습니다.");
        }
      } else {
        alert("로그인이 필요합니다.");
        this.$router.push("/user/signin");
      }
    },
    registerReply() {
      if (this.userInfo) {
        this.reply.username = this.userInfo.username;
        console.log("전달정보: ", this.reply);
        writeQnaReply(
          this.$route.params.qid,
          this.reply,
          (response) => {
            console.log("registerReply - ", response.data);
          },
          (error) => {
            console.log(error);
          }
        );
        this.$router.go(this.$router.currentRoute);
        // this.$router.push("/qna/detail/" + this.$route.params.qid);
      } else {
        alert("로그인이 필요합니다.");
        this.$router.push("/user/signin");
      }
    },
  },
};
</script>

<style></style>
