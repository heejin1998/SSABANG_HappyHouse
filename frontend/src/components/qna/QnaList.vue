<template>
  <div>
    <v-card-title>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="qnas"
      :items-per-page="itemsPerPage"
      @click:row="viewQna"
      :search="search"
      class="elevation-1"
    >
    </v-data-table>
    <v-row justify="end" class="ma-3">
      <v-col cols="auto">
        <v-btn outlined color="deep-orange" @click="moveWrite">글 쓰기</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// import { listQna } from "@/api/qna.js";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaList",
  data() {
    return {
      search: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        { text: "번호", align: "center", sortable: false, value: "qid" },
        { text: "제목", align: "center", sortable: false, value: "title" },
        { text: "작성자", align: "center", sortable: false, value: "username" },
        { text: "작성일", align: "center", sortable: false, value: "regtime" },
      ],
      items: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(qnaStore, ["qnas"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    this.create(param);
    // listQna(
    //   param,
    //   (response) => {
    //     this.items = response.data;
    //     console.log("qna list get");
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  },
  methods: {
    ...mapActions(qnaStore, ["listQna"]),
    create(param) {
      this.listQna(param);
    },
    moveWrite() {
      console.log("write - userInfo: ", this.userInfo);
      if (this.userInfo) {
        this.$router.push({ name: "qnaRegister" });
      } else {
        alert("회원가입이 필요합니다.");
        this.$router.push("/user/signin");
      }
    },
    viewQna(items) {
      this.$router.push({
        name: "qnaDetail",
        params: { qid: items.qid },
      });
    },
  },
};
</script>

<style></style>
