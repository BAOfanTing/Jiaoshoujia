<template>
  <div>
    <el-main>
      <el-scrollbar>
        <el-table :data="tableData">
          <el-table-column prop="id" label="Id" width="200" />
          <el-table-column prop="name" label="姓名" width="200" />
          <el-table-column prop="age" label="年龄" width="200" />
          <el-table-column prop="sex" label="性别" width="200">
            <template #default="scope">
              <el-tag :type="scope.row.sex == 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex == 1 ? '男'
                : '女'}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="roleId" label="角色" width="200" />
          <el-table-column prop="phone" label="手机号" width="200" />
          <el-table-column prop="operation" label="操作" width="200">
            <template #default="scope">
              <el-button type="primary" plain>编辑</el-button>
              <el-button type="danger" plain>删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-scrollbar>
    </el-main>
  </div>

  <div>
     <el-pagination background layout="prev, pager, next" :total="1000" />
  </div>

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
let pageNumber = ref(1);
let pageSize = ref(10);
let total = ref(0);




onMounted(() => {
  // 使用request实例发送请求，不需要写完整的URL
  const queryParam = {
    pageNum: pageNumber.value,
    pageSize: pageSize.value,
    param: {
      name: '' // 如果有name查询条件可以在这里设置
    }
  }

  request.post('/user/listPageC',{params: queryParam}).then(res => res.data).then(res => {
    console.log(res)
    if (res.code == 200) {
      tableData.value = res.data;
      pageNumber.value = res.pageNum;
      pageSize.value = res.pageSize;
      total.value = res.total;
    }
    else {
      alert(res.msg);
    }

  }).catch(error => {
    console.error('请求失败:', error)
  })

  // request.post('/user/Search?name=test').then(res => res.data).then(res => {
  //   console.log(res)
  // }).catch(error => {
  //   console.error('请求失败:', error)
  // })
})

</script>

<style scoped>
/* 让el-main占满整个空间 */
.el-main {
  padding: 0;
  height: 100%;
  overflow: hidden;
  margin: 0;
  display: flex;
  flex-direction: column;
}

/* 让el-scrollbar占满整个el-main */
:deep(.el-scrollbar) {
  flex: 1;
  width: 100%;
  min-height: 0;
}

/* 让el-table占满整个el-scrollbar */
:deep(.el-table) {
  width: 100%;
  height: 100%;
  margin: 0;
}

/* 让el-table__wrapper占满整个el-table */
:deep(.el-table__wrapper) {
  height: 100%;
  width: 100%;
}

/* 让el-table__body-wrapper占满剩余空间 */
:deep(.el-table__body-wrapper) {
  flex: 1;
  overflow: auto;
}

/* 确保表格头部和主体都能正确显示 */
:deep(.el-table__header-wrapper),
:deep(.el-table__body-wrapper) {
  width: 100%;
}
</style>