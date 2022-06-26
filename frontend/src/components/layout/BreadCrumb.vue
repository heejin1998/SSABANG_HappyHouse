<template>
    <v-container fluid class="breadcrumb px-16 pt-0">
        <div class="text-h5 orange--text text--darken-3" v-text="title"></div>
    </v-container>
</template>

<script>
export default {
    name: "BreadCrumb",
    props: ["title"],
};
</script>

<style>
.breadcrumb {
    border-bottom: 2px #ffa726 solid;
}
</style>
