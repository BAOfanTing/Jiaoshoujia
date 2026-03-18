<template>
  <el-main>
    <el-scrollbar>
      <el-table :data="tableData">
        <el-table-column prop="id" label="Id" width="140" />
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="age" label="年龄" width="120" />
        <el-table-column prop="sex" label="性别" width="120">
          <template #default="scope">
            <el-tag :type="scope.row.sex == 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex == 1 ? '男' : '女'}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="roleId" label="角色" width="120" />
        <el-table-column prop="phone" label="手机号" width="120" />
        <el-table-column prop="operation" label="操作" width="120" />
      </el-table>
    </el-scrollbar>
  </el-main>

</template>

<script setup lang="ts" name="Main">
import { ref, onMounted } from 'vue';
import axios from 'axios';

// 创建axios实例，配置baseURL
const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 5000
});

const tableData = ref();

onMounted(() => {
  // 使用request实例发送请求，不需要写完整的URL
  request.get('/user/list').then(res => res.data).then(res => {
    console.log(res)
    tableData.value = res

  }).catch(error => {
    console.error('请求失败:', error)
  })

  request.post('/user/Search?name=test').then(res => res.data).then(res => {
    console.log(res)
  }).catch(error => {
    console.error('请求失败:', error)
  })
})

</script>