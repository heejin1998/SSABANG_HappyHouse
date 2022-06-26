import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import MainView from "@/views/MainView.vue";
import HouseView from "@/views/HouseView.vue";
import NewsView from "@/views/NewsView.vue";
import KakaoLogin from "@/components/user/KakaoLogin.vue";
Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "main",
        component: MainView,
    },
    {
        path: "/main",
        name: "main",
        component: MainView,
    },
    {
        path: "/home",
        name: "home",
        component: HomeView,
    },
    {
        path: "/house",
        name: "house",
        component: HouseView,
        redirect:"/house/map",
        children: [
            {
                path: "map",
                name: "houseInfoList",
                component: () => import("@/components/house/HouseInfoList.vue"),
            },
            {
              path: "item/:aptCode",
              name: "houseInfo",
              component: () => import("@/components/house/HouseInfo.vue")
            }
        ],
    },
    {
        path: "/news",
        name: "news",
        component: NewsView,
    },
    {
        path: "/qna",
        name: "qna",
        component: () => import("@/views/QnaView.vue"),
        redirect: "/qna/list",
        children: [
            {
                path: "list",
                name: "qnaList",
                component: () => import("@/components/qna/QnaList.vue"),
            },
            {
                path: "write",
                name: "qnaRegister",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/qna/QnaRegister.vue"),
            },
            {
                path: "detail/:qid",
                name: "qnaDetail",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/qna/QnaDetail.vue"),
            },
            {
                path: "modify/:qid",
                name: "qnaModify",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/qna/QnaModify.vue"),
            },
        ],
    },
    {
        path: "/notice",
        name: "notice",
        component: () => import("@/views/NoticeView.vue"),
        redirect: "/notice/list",
        children: [
            {
                path: "list",
                name: "noticeList",
                component: () => import("@/components/notice/NoticeList.vue"),
            },
            {
                path: "write",
                name: "noticeRegister",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/notice/NoticeRegister.vue"),
            },
            {
                path: "detail/:nid",
                name: "noticeDetail",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/notice/NoticeDetail.vue"),
            },
            {
                path: "modify/:nid",
                name: "noticeModify",
                // beforeEnter: onlyAuthUser,
                component: () => import("@/components/notice/NoticeModify.vue"),
            },
        ],
    },
    {
        path: "/user",
        name: "user",
        component: () => import("@/views/MemberView.vue"),
        children: [
            {
                path: "signin",
                name: "signIn",
                component: () => import("@/components/user/MemberLogin.vue"),
            },
            {
                path: "mypage",
                name: "mypage",
                //  beforeEnter: onlyAuthUser,
                component: () => import("@/components/user/MemberMyPage.vue"),
            },
            {
                path: "modify",
                name: "modify",
                component: () => import("@/components/user/MemberModify.vue"),
            },
            {
                path: "favor",
                name: "favor",
                component: () => import("@/components/user/MemberFavor.vue"),
            },

        ],
    },
    {
        path: "/klogin",
        name: "kakaoLogin",
        component: KakaoLogin,
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;
