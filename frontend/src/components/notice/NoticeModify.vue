<template>
  <v-container>
    <v-card>
      <v-form class="pa-8" ref="form" v-model="valid" lazy-validation>
        제목
        <v-text-field
          v-model="item.title"
          :counter="50"
          label="title"
          required
        ></v-text-field>
        내용
        <v-textarea
          solo
          v-model="item.content"
          label="content"
          required
        ></v-textarea>
        <v-btn outlined color="deep-orange" class="mr-4" @click="modifyNotice">
          수정
        </v-btn>

        <v-btn outlined color="deep-orange" class="mr-4" @click="reset">
          Reset
        </v-btn>
        <v-btn outlined color="deep-orange" class="mr-4" @click="moveNotice">
          목록으로 돌아가기
        </v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import { modifyNotice } from "@/api/notice";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeModify",
  data: () => ({
    valid: true,
    nid: "",
    item: {
      username: "",
      title: "",
      content: "",
    },
    titleRules: [
      (v) => !!v || "Title is required",
      (v) => (v && v.length <= 50) || "Title must be less than 50 characters",
    ],
    contentRules: [(v) => !!v || "Content is required"],
  }),
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(noticeStore, ["notice"]),
  },
  created() {
    this.create(this.$route.params.nid);
  },

  methods: {
    ...mapActions(noticeStore, ["getNotice"]),
    create(nid) {
      this.item.username = this.notice.username;
      this.item.title = this.notice.title;
      this.item.content = this.notice.content;
      console.log("create - ", this.item);
      this.getNotice(nid);
    },
    async modifyNotice() {
      await modifyNotice(
        this.$route.params.nid,
        {
          username: this.item.username,
          title: this.item.title,
          content: this.item.content,
        },
        (response) => {
          // let msg = "수정 처리시 문제가 발생했습니다.";
          if (response.data === "success") {
            // msg = "수정이 완료되었습니다.";
            console.log("수정 완료");
          }
          // alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      this.moveNotice();
    },
    moveNotice() {
      this.nid = this.$route.params.nid;
      this.$router.push(`/notice/detail/${this.nid}`);
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style></style>
