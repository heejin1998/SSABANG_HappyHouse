import { listFavor } from "@/api/user.js";
const memberStore = {
    namespaced: true,
    state: {
        isLogin: false,
        isLoginError: false,
        isRegUser: false,
        userInfo: null,
        favors: [],
    },
    getters: {
        checkUserInfo: function (state) {
            return state.userInfo;
        },
    },
    mutations: {
        SET_IS_LOGIN: (state, isLogin) => {
            state.isLogin = isLogin;
        },
        SET_IS_LOGIN_ERROR: (state, isLoginError) => {
            state.isLoginError = isLoginError;
        },
        SET_USER_INFO: (state, userInfo) => {
            state.isLogin = true;
            state.userInfo = userInfo;
        },
        SET_IS_USER: (state, isUser) => {
            state.isRegUser = isUser;
        },
        LIST_FAVOR: (state, favors) => {
            state.favors = favors;
        },
    },
    actions: {
        listFavor({ commit }, kid) {
            listFavor(
                kid,
                (response) => {
                    commit("LIST_FAVOR", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        }
    },
};

export default memberStore;
