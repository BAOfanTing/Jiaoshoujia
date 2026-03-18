<template>
<el-main>
        <el-scrollbar>
          <el-table :data="tableData">
            <el-table-column prop="date" label="Date" width="140" />
            <el-table-column prop="name" label="Name" width="120" />
            <el-table-column prop="address" label="Address" />
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

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({ length: 20 }).fill(item))

onMounted(() => {
  // 使用request实例发送请求，不需要写完整的URL
  request.get('/user/list').then(res => res.data).then(res => {
    console.log(res)
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