<template>
    <div mode="rerative" class="page-wrap">
        <bread-crumb title="아파트 매매"></bread-crumb>
        <house-container id="house-container"></house-container>
    </div>
</template>

<script>
import BreadCrumb from "@/components/layout/BreadCrumb.vue";
import HouseContainer from "@/components/house/HouseContainer.vue";
export default {
    components: { BreadCrumb, HouseContainer },
};
</script>

<style scope>

.page-wrap {
    display: flex;
    flex-direction: column;
    max-height: 100%;
    flex: 1 0 auto;
}

#house_container{
  max-height: 500px;
}
</style>
