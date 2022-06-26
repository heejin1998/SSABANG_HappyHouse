<template>
  <div>
    <v-card-title>
      <!-- 공지사항 -->
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
      :items="notices"
      :items-per-page="itemsPerPage"
      @click:row="viewNotice"
      :search="search"
      class="elevation-1"
    >
    </v-data-table>

    <v-row justify="end" class="ma-3">
      <v-col cols="auto">
        <v-btn @click="moveWrite()" outlined color="deep-orange">글 쓰기</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeList",
  data() {
    return {
      search: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        // { text: "번호", align: "center", sortable: false, value: "nid" },
        // { text: "제목", align: "center", sortable: false, value: "title" },
        // { text: "작성자", align: "center", sortable: false, value: "username" },
        // { text: "작성일", align: "center", sortable: false, value: "regtime" },

        { text: "제목", align: "center", sortable: false, value: "title" },

        { text: "작성일", align: "center", sortable: false, value: "regtime" },
      ],
      items: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(noticeStore, ["notices"]),
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    this.create(param);
  },
  methods: {
    ...mapActions(noticeStore, ["listNotice"]),
    create(param) {
      this.listNotice(param);
    },
    moveWrite() {
      if (this.userInfo.username === "admin") {
        this.$router.push({ name: "noticeRegister" });
      } else {
        alert("접근 권한이 없습니다.");
      }
    },
    viewNotice(items) {
      this.$router.push({
        name: "noticeDetail",
        params: { nid: items.nid },
      });
    },
  },
};
</script>

<style></style>
