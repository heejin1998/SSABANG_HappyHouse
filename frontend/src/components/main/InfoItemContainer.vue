<template>
  <div class="d-flex flex-row align-center justify-space-around">
    <info-item :itemList="newsItems" title="뉴스" type="news"></info-item>
    <info-item
      :itemList="noticeItems"
      title="공지사항"
      type="notice"
    ></info-item>
    <info-item
      :itemList="qnaItems"
      :to="qnaItems.to"
      title="QnA"
      type="qna"
    ></info-item>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import InfoItem from "./InfoItem.vue";
const newsStore = "newsStore";
const noticeStore = "noticeStore";
const qnaStore = "qnaStore";

export default {
  components: { InfoItem },
  name: "InfoItemContainer",
  computed: {
    ...mapState(newsStore, ["newsList"]),
    ...mapState(noticeStore, ["notices"]),
    ...mapState(qnaStore, ["qnas"]),
  },
  methods: {
    ...mapActions(newsStore, ["getNews"]),
    ...mapActions(noticeStore, ["listNotice"]),
    ...mapActions(qnaStore, ["listQna"]),
    viewNotice(itemList) {
      this.$router.push({
        name: "noticeDetail",
        params: { nid: itemList.nid },
      });
    },
  },
  data() {
    return {
      newsItems: [],
      noticeItems: [],
      qnaItems: [],
    };
  },
  created() {
    this.getNews();
    this.listNotice();
    this.listQna();
  },
  watch: {
    newsList(value) {
      for (let i = 0; i < 5; i++) {
        let item = value[i];
        this.newsItems.push({
          content: item.title,
          link: item.link,
          type: "news",
        });
      }
    },
    notices(value) {
      for (let i = 0; i < 5; i++) {
        let item = value[i];
        this.noticeItems.push({
          content: item.title,
          to: `notice/detail/${item.nid}`,
          type: "notice",
        });
      }
    },
    qnas(value) {
      for (let i = 0; i < 5; i++) {
        let item = value[i];
        this.qnaItems.push({
          content: item.title,
          to: `qna/detail/${item.qid}`,
          type: "qna",
        });
      }
    },
  },
};
</script>

<style></style>
