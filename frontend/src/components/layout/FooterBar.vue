<template>
  <v-card flat tile class="grey lighten-3 black--text text-center mt-20">
    <v-card-text>
      <v-btn v-for="icon in icons" :key="icon" class="mx-4 black--text" icon>
        <v-icon size="24px">
          {{ icon }}
        </v-icon>
      </v-btn>
    </v-card-text>

    <v-card-text class="footer-text black--text pt-0">
      <!-- Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet.
        Mauris cursus commodo interdum. Praesent ut risus eget metus luctus
        accumsan id ultrices nunc. Sed at orci sed massa consectetur dignissim a
        sit amet dui. Duis commodo vitae velit et faucibus. Morbi vehicula
        lacinia malesuada. Nulla placerat augue vel ipsum ultrices, cursus
        iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor
        vel ut orci. Orci varius natoque penatibus et magnis dis parturient
        montes, nascetur ridiculus mus. -->
      <p>삼성청년SW아카데미(SSAFY)</p>
      <p>주소 : 서울특별시 강남구 테헤란로 멀티스퀘어</p>

      <p>문의 : 1544-9001 | 이메일 : admin@ssafy.com</p>
    </v-card-text>

    <v-divider></v-divider>

    <v-card-text class="black--text">
      Copyright © SSAFY All Rights Reserved.
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
  }),
};
</script>

<style scoped>
.footer-text {
  width: 100%;
}
</style>
