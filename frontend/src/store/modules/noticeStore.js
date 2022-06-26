import { listNotice, getNotice } from "@/api/notice.js";
const noticeStore = {
  namespaced: true,
  state: {
    notices: [],
    notice: {},
  },
  getters: {},
  mutations: {
    LIST_NOTICE(state, noticeList) {
      state.notices = noticeList;
      console.log("mutations - list notice: ", state.notices);
    },
    GET_NOTICE(state, noticeItem) {
      state.notice = noticeItem;
      console.log("mutations - get_notice : ", state.notice);
    },
  },
  actions: {
    listNotice({ commit }, param) {
      listNotice(
        param,
        (response) => {
          commit("LIST_NOTICE", response.data);
          console.log("notice list get");
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getNotice({ commit }, nid) {
      getNotice(
        nid,
        (response) => {
          commit("GET_NOTICE", response.data);
        },
        (error) => {
          console.log("get notice 때 에러!!", error);
        }
      );
    },
  },
};

export default noticeStore;
