import { newsList } from "@/api/news.js";

const newsStore = {
    namespaced: true,
    state: {
        newsList: [],
    },
    getters: {},
    mutations: {
        SET_NEWS_LIST: (state, news) => {
            state.newsList = news.items;
        },
    },
    actions: {
        getNews: ({ commit }) => {
            newsList(
                ({ data }) => {
                    commit("SET_NEWS_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};

export default newsStore;
