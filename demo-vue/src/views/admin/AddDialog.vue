<template>
  <el-dialog v-model="visible" :title="form.id ? '修改用户' : '新增用户'" width="400">
    <el-form ref="formRef" :model="form" label-width="auto" style="max-width: 600px" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" style="width: 50%;" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" style="width: 50%;" />
      </el-form-item>
      <el-form-item label="密码" prop="userpwd">
        <el-input v-model="form.userpwd" type="password" style="width: 50%;" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" style="width: 50%;" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio value="1">男</el-radio>
          <el-radio value="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" style="width: 50%;" />
      </el-form-item>
      <el-form-item label="头像" prop="headurl">
        <el-input v-model="form.headurl" style="width: 50%;" /><el-button type="primary"
          @click="handleUpload">上传</el-button>
      </el-form-item>

    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit" v-loading="btnLoading">确认确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts" name="UserForm">
import { ref, reactive, nextTick } from 'vue';
import { adminAPi } from '@/api/admin-api';
import { ElMessage } from 'element-plus';

// 公开方法，用于在父组件中调用
defineExpose({
  showModel,
})

// 弹窗是否显示
const visible = ref(false);
// 按钮加载中
const btnLoading = ref(false);

//自定义事件,用于刷新列表
const emit = defineEmits(['refresh']);

//表单组件
const formRef = ref();
// 表单初始值
const formDefault = {
  id: undefined,
  username: undefined,
  name: undefined,
  age: undefined,
  sex: undefined,
  phone: undefined,
  headurl: undefined,
  userpwd: undefined,
}

let form = reactive({ ...formDefault });


const rules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 5, message: '用户名长度必须在3-5位之间', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
  ],
  userpwd: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 3, max: 15, message: '密码长度必须在3-15位之间', trigger: 'blur' },
  ],
  age: [
    { required: true, message: '请输入年龄', trigger: 'blur' },
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' },
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
  ],
  headurl: [
    { required: true, message: '请上传头像', trigger: 'blur' },
  ],
});



// 显示弹窗
function showModel(row) {
  if (row) {
    //修改
    Object.assign(form, row);
  }
  else {
    //新增
    Object.assign(form, formDefault);
  }
  // 表单附初始值

  visible.value = true;
}


function handleUpload() {
  // 上传头像逻辑
}

function handleSubmit() {
  btnLoading.value = true;

  try {
    formRef.value.validate().then(async () => {
      if(form.id)
    {
      //修改
      await adminAPi.update(form);
    }
    else{
      //新增
      await adminAPi.add(form);
    }
    ElMessage.success(form.id ? '修改成功' : '新增成功');
      visible.value = false;
      btnLoading.value = false;
      //触发事件
      emit('refresh');
    });
  }
  catch (error) {

  }
  finally {
    btnLoading.value = false;
  }

}

</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>
