<template>
    <el-header class="header">
        <div class="header-left">
            <h1 class="logo">欢迎来到仓库管理系统</h1>
        </div>
    </el-header>
</template>

<script setup lang="ts" name="Header">
import { ref } from 'vue';

const activeIndex = ref('1');

const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath);
};
</script>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    background-color: #409EFF;
    color: white;
    height: 60px;
}

.logo {
    margin: 0;
    font-size: 20px;
    font-weight: bold;
}

.header-right {
    flex: 1;
    display: flex;
    justify-content: flex-end;
}

.el-menu {
    background-color: transparent;
    border-bottom: none;
}

.el-menu-item {
    color: white;
}

.el-menu-item:hover {
    background-color: rgba(255, 255, 255, 0.1);
}

.el-menu-item.is-active {
    color: #FFD04B;
    font-weight: bold;
}
</style>