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
        <v-btn
          :disabled="!valid"
          class="mr-4"
          @click="modifyQna"
          outlined
          color="deep-orange"
        >
          수정
        </v-btn>

        <v-btn outlined color="deep-orange" class="mr-4" @click="reset">
          Reset
        </v-btn>
        <v-btn outlined color="deep-orange" @click="moveQna">
          목록으로 돌아가기
        </v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import { modifyQna, getQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnaModify",
  data: () => ({
    valid: true,
    qid: "",
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
  },
  created() {
    getQna(
      this.$route.params.qid,
      (response) => {
        this.item = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },

  methods: {
    async modifyQna() {
      await modifyQna(
        this.$route.params.qid,
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
      this.moveQna();
    },
    moveQna() {
      this.qid = this.$route.params.qid;
      this.$router.push(`/qna/detail/${this.qid}`);
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style></style>
