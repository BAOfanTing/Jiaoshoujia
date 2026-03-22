<template>
    <div class="common-layout" style="height: 100vh; width: 100%;">
        <el-container direction="vertical" style="height: 100%;">
            <el-container direction="horizontal" style="flex: 1;">
                <Aside></Aside>
                <el-container direction="vertical" style="flex: 1;">
                    <Header></Header>
                    <Main></Main>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup lang="ts" name="index">
import Header from '../components/Header.vue'
import Aside from '../components/Aside.vue'
import Main from '../components/Main.vue'
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