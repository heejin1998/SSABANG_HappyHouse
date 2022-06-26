<template>
    <div>
        <v-container><div class="my-12"></div></v-container>
        <section id="grid">
            <v-container>
                <v-row justify="space-around">
                    <v-col cols="auto">
                        <v-responsive width="350">
                            <h2 class="welcome-text">WELCOME TO SSABANG</h2>
                            <v-divider class="my-8" />

                            <!-- <p class="text-success mt-3">Available until Jun 16</p> -->

                            <p class="intro-text mt-3">아파트 실거래가부터 건물 실거래가 정보까지 빠르게!</p>

                            <p class="intro-text mt-8">원하는 집을 찾기 위한 맞춤검색 가격, 구조, 층수까지 한눈에</p>
                            <p class="intro-text mt-8">역세권, 학세권까지 지도에서 매물정보를 한눈에!</p>

                            <v-btn class="mt-6" color="orange darken-1 white--text" to="/house"> Try it out </v-btn>
                        </v-responsive>
                    </v-col>

                    <v-img max-width="700" src="@/assets/home-house.png" />
                </v-row>
            </v-container>
        </section>
    </div>
</template>

<script>
export default {};
</script>

<style>
.welcome-text {
    font-family: "Open Sans", sans-serif;
    font-size: xx-large;
}

.intro-text {
    font-family: "Nanum Gothic", sans-serif;
}
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Jua&family=Open+Sans:wght@600&display=swap");
</style>
