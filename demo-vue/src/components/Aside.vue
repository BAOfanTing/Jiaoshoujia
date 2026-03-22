<template>
    <el-aside width="200px" class="aside">
        <el-menu
            :default-active="activeIndex"
            class="aside-menu"
            mode="vertical"
            @select="handleSelect"
        >
            <el-menu-item index="1">
                <el-icon><House /></el-icon>
                <span>首页</span>
            </el-menu-item>
            <el-menu-item index="2">
                <el-icon><Menu /></el-icon>
                <span>菜单管理</span>
            </el-menu-item>
            <el-menu-item index="3">
                <el-icon><ShoppingCart /></el-icon>
                <span>订单管理</span>
            </el-menu-item>
            <el-menu-item index="4">
                <el-icon><User /></el-icon>
                <span>用户中心</span>
            </el-menu-item>
            <el-menu-item index="5">
                <el-icon><Setting /></el-icon>
                <span>系统设置</span>
            </el-menu-item>
        </el-menu>
    </el-aside>
</template>

<script setup lang="ts" name="Aside">
import { ref } from 'vue';
import { House, Menu, ShoppingCart, User, Setting } from '@element-plus/icons-vue';

const activeIndex = ref('1');

const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath);
};
</script>

<style scoped>
.aside {
    background-color: #f0f2f5;
    height: 100vh;
    overflow-y: auto;
}

.aside-menu {
    border-right: none;
    height: 100%;
}

.el-menu-item {
    height: 60px;
    line-height: 60px;
    font-size: 14px;
}

.el-menu-item:hover {
    background-color: rgba(64, 158, 255, 0.1);
}

.el-menu-item.is-active {
    background-color: #ecf5ff;
    color: #409EFF;
    font-weight: bold;
}

.el-icon {
    margin-right: 10px;
}
</style>