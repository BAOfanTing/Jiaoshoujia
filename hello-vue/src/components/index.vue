<template>
    <div class="common-layout">
        <el-container direction="vertical">
            <el-container direction="horizontal">
                <Aside></Aside>
                <el-container direction="vertical">
                    <Header></Header>
                    <Main></Main>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup lang="ts" name="index">
import Header from './Header.vue'
import Aside from './Aside.vue'
import Main from './Main.vue'
</script>

<style scoped>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
    }
    .el-aside {
        color:#333;
    }
</style>