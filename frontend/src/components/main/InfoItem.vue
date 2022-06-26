<template>
    <div class="pa-6 item-box d-flex flex-column">
        <div class="text-h6 title pb-2 mb-3">
            {{ title }}
            <div class="plus-logo">
                <i class="fa-solid fa-plus" @click="movePage()"></i>
            </div>
        </div>

        <div class="item" v-for="(item, index) in itemList" :key="index" v-html="item.content" @click="viewItem(item)"></div>
    </div>
</template>

<script>
export default {
    name: "InfoItem",
    props: ["title", "itemList", "type"],
    methods: {
        viewItem(item) {
            if (item.type == "qna") {
                this.$router.push(item.to);
            } else if (item.type == "notice") {
                console.log("notice");
                this.$router.push(item.to);
            } else if (item.type == "news") {
                window.open(item.link);
            }
        },
        movePage() {
            if (this.type == "news") {
                this.$router.push({
                    name: "news",
                });
            } else if (this.type == "notice") {
                this.$router.push({
                    name: "noticeList",
                });
            } else if (this.type == "qna") {
                this.$router.push({
                    name: "qnaList",
                });
            }
        },
    },
};
</script>

<style>
.item {
    cursor: pointer;
}

.item-box {
    width: 340px;
}

.item-box .title {
    font-weight: bold;
    border-bottom: 1px black solid;
}

.item-box .item {
    width: 300px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    line-height: 2rem;
}

.plus-logo {
    display: inline;
    float: right;
    margin-right: 10px;
    color: gray;
    cursor: pointer;
}
</style>
