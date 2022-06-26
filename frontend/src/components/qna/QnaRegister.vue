<template>
  <v-container>
    <v-card>
      <v-form class="pa-8" ref="form" v-model="valid" lazy-validation>
        제목
        <v-text-field
          v-model="item.title"
          :counter="50"
          :rules="titleRules"
          label="title"
          required
        ></v-text-field>
        내용
        <v-textarea
          solo
          v-model="item.content"
          :rules="contentRules"
          label="content"
          required
        ></v-textarea>
        <v-btn
          :disabled="!valid"
          outlined
          color="deep-orange"
          class="mr-4"
          @click="registerQna"
        >
          작성
        </v-btn>
        <v-btn outlined color="deep-orange" class="mr-4" @click="reset">
          Reset
        </v-btn>
        <v-btn outlined color="deep-orange" to="/qna">
          목록으로 돌아가기
        </v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import { writeQna } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "QnaRegister",
  created() {
    // this.user.username = this.userInfo.username;
  },
  data: () => ({
    valid: true,
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

  methods: {
    registerQna() {
      console.log(this.item.title);
      writeQna(
        {
          username: this.userInfo.username,
          title: this.item.title,
          content: this.item.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      this.$router.push({ name: "qnaList" });
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style></style>
