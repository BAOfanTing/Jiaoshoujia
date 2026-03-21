<template>

  <div style="display: flex; align-items: center; gap: 10px; margin: 10px;">
    <el-input placeholder="请输入姓名" v-model="searchName" @keyup.enter="loadDataC1" suffix-icon="el-icon-search"
      style="width: 200px;"></el-input>
    <el-select v-model="searchSex" placeholder="请选择性别" style="width: 240px">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>

    <el-button type="primary" @click="loadDataC1">查询</el-button>

    <el-button plain @click="dialogVisible = true">
      新增
    </el-button>
    <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="500"
    >
    //新增用户表单
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <el-form-item label="编号">
        <el-input v-model="form.no" />
      </el-form-item>
    <el-form-item label="姓名">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="年龄">
      <el-input v-model="form.age" />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password" />
    </el-form-item>
    <el-form-item label="手机号">
      <el-input v-model="form.phone" />
    </el-form-item>
    <el-form-item label="角色">
      <el-radio-group v-model="form.roleId">
        <el-radio value="1">管理员</el-radio>
        <el-radio value="2">普通用户</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="form.sex">
        <el-radio value="1">男</el-radio>
        <el-radio value="0">女</el-radio>
      </el-radio-group>
    </el-form-item>
    
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false; addUser()">
          确认
        </el-button>
      </div>
    </template>
    </el-dialog>
  </div>
  <div>
    <el-main>
      <el-scrollbar>
        <el-table :data="tableData">
          <el-table-column prop="id" label="Id" width="150" />
          <el-table-column prop="name" label="姓名" width="140" />
          <el-table-column prop="age" label="年龄" width="120" />
          <el-table-column prop="sex" label="性别" width="120">
            <template #default="scope">
              <el-tag :type="scope.row.sex == 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex == 1 ? '男'
                : '女' }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="roleId" label="角色" width="120" />
          <el-table-column prop="phone" label="手机号" width="140" />
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

  <div class="demo-pagination-block">
    <div class="demonstration">Change page size</div>
    <el-pagination v-model:current-page="pageNumber" v-model:page-size="pageSize" :page-sizes="[5, 10, 20, 50]"
      :size="size" :disabled="disabled" :background="background" layout="sizes, prev, pager, next" :total="total"
      @size-change="handleSizeChange" @current-change="handleCurrentChange" />
  </div>
</template>

<script setup lang="ts" name="Main">
import { ref, onMounted, reactive } from 'vue';
import axios from 'axios';
import type { ComponentSize } from 'element-plus'
let searchName = ref('');
let searchSex = ref('');
let dialogVisible = ref(false);

// 创建axios实例，配置baseURL
const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 5000
});

const tableData = ref();
let pageNumber = ref(1);
let pageSize = ref(5);
let total = ref(0);

const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)
const form = reactive({
  no: '',
  name: '',
  password: '',
  age: '',
  sex: '',
  phone: '',
  roleId: '',
})
//改变每页条数时重置页码为第一页,并重新请求数据
const handleSizeChange = (val: number) => {
  pageSize.value = val;
  pageNumber.value = 1;
  loadData();
}

//点击页码时,更新当前页重新请求数据
const handleCurrentChange = (val: number) => {
  pageNumber.value = val;
  loadData();
}

const options = [
  {
    value: '1',
    label: '男',
  },
  {
    value: '0',
    label: '女',
  }
]

function addUser() {
  request.post('/user/add', form).then(res => res.data).then(res => {
    console.log(res)
    if (res.code == 200) {
      alert(res.msg);
      dialogVisible.value = false;
      loadData();
    } else {
      alert(res.msg);
    }
  }).catch(error => {
    console.error('请求失败:', error)
  })
}

/**
 * 自定义查询用户列表
 */
const loadDataC1 = () => {
  const queryParam = {
    pageNum: pageNumber.value,
    pageSize: pageSize.value,
    param: {
      name: searchName.value, // 如果有name查询条件可以在这里设置
      sex: searchSex.value // 如果有sex查询条件可以在这里设置
    }
  }

  request.post('/user/listPageC1', queryParam).then(res => res.data).then(res => {
    console.log("后端返回数据:", res)
    if (res.code == 200) {
      tableData.value = res.data;
      total.value = res.total; // 正确赋值总条数 
    } else {
      alert(res.msg);
    }
  }).catch(error => {
    console.error('请求失败:', error)
  })
}
const loadData = () => {
  const queryParam = {
    pageNum: pageNumber.value,
    pageSize: pageSize.value,
    param: {
      name: searchName.value // 如果有name查询条件可以在这里设置
    }
  }

  request.post('/user/listPageC', queryParam).then(res => res.data).then(res => {
    console.log("后端返回数据:", res)
    if (res.code == 200) {
      tableData.value = res.data;
      total.value = res.total; // 正确赋值总条数 
    } else {
      alert(res.msg);
    }
  }).catch(error => {
    console.error('请求失败:', error)
  })
}


//页面初始化调用一下loadData方法
onMounted(() => {
  loadData();

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