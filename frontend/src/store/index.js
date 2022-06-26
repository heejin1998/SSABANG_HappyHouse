import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import houseStore from "@/store/modules/houseStore.js";
import newsStore from "@/store/modules/newsStore.js";
import memberStore from "@/store/modules/memberStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import mapStore from "@/store/modules/mapStore.js";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    houseStore,
    newsStore,
    memberStore,
    noticeStore,
    qnaStore,
    mapStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
      paths: ["memberStore", "noticeStore", "qnaStore"],
    }),
  ],
});
