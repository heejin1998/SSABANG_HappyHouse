import { listQna, getQna } from "@/api/qna.js";
const qnaStore = {
  namespaced: true,
  state: {
    qnas: [],
    qna: {},
  },
  getters: {},
  mutations: {
    LIST_QNA(state, qnaList) {
      state.qnas = qnaList;
    },
    GET_QNA(state, qnaItem) {
      state.qna = qnaItem;
    },
  },
  actions: {
    listQna({ commit }, param) {
      listQna(
        param,
        (response) => {
          commit("LIST_QNA", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getQna({ commit }, qid) {
      getQna(
        qid,
        (response) => {
          commit("GET_QNA", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default qnaStore;
