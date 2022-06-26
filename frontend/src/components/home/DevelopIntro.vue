<template>
    <div>
        <v-container><div class="my-12"></div></v-container>
        <section id="grid">
            <v-container>
                <v-row justify="space-around">
                    <v-col cols="auto">
                        <v-responsive width="350">
                            <h2 class="title-text">Develop By</h2>
                            <v-divider class="my-8" />
                        </v-responsive>
                    </v-col>
                    <div style="width:700px"></div>
                </v-row>
                <v-row class="mr-12 ml-12">
                    <v-col align="center">
                        <v-card class="pt-10 pb-10" max-width="300">
                            <div class="box">
                                <img class="profile" src="@/assets/yh.png" alt="" />
                            </div>
                            <h2 class="intro-text ma-8">김용환</h2>
                            <v-divider class="my-8" />
                            <p class="intro-text">메인 페이지</p>
                            <p class="intro-text">아파트 매매</p>
                            <p class="intro-text">뉴스 크롤링</p>
                             <p class="intro-text">관심 매물</p></v-card
                        >
                    </v-col>
                    <v-col align="center">
                        <v-card class="pt-10 pb-10" max-width="300">
                            <div class="box">
                                <img class="profile" src="@/assets/hj.png" alt="" />
                            </div>
                            <h2 class="intro-text ma-8">이희진</h2>
                            <v-divider class="my-8" />
                            <p class="intro-text">소셜 로그인 및 회원관리</p>
                            <p class="intro-text">공지사항, Q&A</p>
                            <p class="intro-text">관심매물</p>
                             <p class="intro-text">주변 편의시설</p></v-card
                        >
                    </v-col>
                </v-row>
            </v-container>
        </section>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.title-text {
    font-family: "Open Sans", sans-serif;
    font-size: xx-large;
}

.box {
    width: 150px;
    height: 160px;
    /* border-radius: 70%; */
    overflow: hidden;
}
.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.intro-text {
    font-family: "Nanum Gothic", sans-serif;
}
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Jua&family=Open+Sans:wght@600&display=swap");
</style>
