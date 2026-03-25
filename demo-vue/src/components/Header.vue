<template>
    <el-header class="header">
        <div class="header-left">
            <h1 class="logo">欢迎来到仓库管理系统</h1>
        </div>
        <div>
            <span>欢迎，{{ admin.name }}</span>
            <el-button type="primary" size="small" @click="logout">退出登录</el-button>
        </div>
    </el-header>
</template>

<script setup lang="ts" name="Header">
import { ref } from 'vue';
import { adminAPi } from '@/api/admin-api';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useAdminStore } from '@/stores/admin';
import { storeToRefs } from 'pinia';

//从状态管理中获取admin信息
const adminStore = useAdminStore();
//转为响应式数据
const{admin} = storeToRefs(adminStore);



const router = useRouter();

async function logout()
{
    try
    {
        let loginResult =await adminAPi.logout();
        ElMessage.success('退出登录成功');
        window.location.href = '/login';
    }
    catch (error)
    {
        console.log(error);
        ElMessage.error('退出登录失败');
    }
}

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