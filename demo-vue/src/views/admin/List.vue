<template>

  <div style="display: flex; align-items: center; gap: 10px; margin: 10px;">
    <el-input placeholder="请输入姓名" v-model="queryForm.name" suffix-icon="el-icon-search"
      style="width: 200px;"></el-input>
    <el-select v-model="queryForm.sex" placeholder="请选择性别" style="width: 240px">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>

    <el-button type="primary" @click="getList()">查询</el-button>

    <el-button plain @click="showAddDialog()">
      新增
    </el-button>
    
    <!-- 使用独立的UserForm组件 -->
    <UserForm v-model="dialogVisible"  />
  </div>
  <div>
    <el-main>
      <el-scrollbar>
        <el-table :data="tableData" v-loading="listLoading">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="username" label="用户名" width="140" />
          <el-table-column prop="name" label="姓名" width="140" />
          <el-table-column prop="age" label="年龄" width="120" />
          <el-table-column prop="sex" label="性别" width="120">
            <template #default="scope">
              <el-tag :type="scope.row.sex == 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex == 1 ? '男'
                : '女' }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="phone" label="手机号" width="140" />
          <el-table-column prop="headurl" label="头像" width="120" />
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
import type { ComponentSize } from 'element-plus'
import { adminAPi } from '@/api/admin-api';
import Constants from '@/utils/constants';

let dialogVisible = ref(false);


//列表数据
const tableData = ref([]);
//列表加载中
const listLoading = ref(false);

let pageNumber = ref(1);
let pageSize = ref(5);
let total = ref(0);

const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)

//表单初始值
const queryFormState = 
{
  name: '',
  sex: '',
  pageNum:1,
  pageSize:Constants.PAGE_SIZE,
}
//表单查询条件对象
const queryForm = reactive({...queryFormState});


async function getList()
{
  try
  {
    listLoading.value = true;
    let responseModel = await adminAPi.queryPageList(queryForm,queryForm.pageNum,queryForm.pageSize);
    tableData.value = responseModel.data.list;
    total.value = responseModel.data.totalCount;
  }
  catch (error)
  {
    console.error(error);
  }
  finally
  {
    listLoading.value = false;
  }
}

onMounted(() => {
  getList();
});

//改变每页条数时重置页码为第一页,并重新请求数据
const handleSizeChange = (val: number) => {
  pageSize.value = val;
  pageNumber.value = 1;
  // loadData();
}

//点击页码时,更新当前页重新请求数据
const handleCurrentChange = (val: number) => {
  pageNumber.value = val;
  // loadData();
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

function showAddDialog() {
  dialogVisible.value = true;
}


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
