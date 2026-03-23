<template>
   <el-dialog v-bind:modelValue="dialogVisible" title="新增用户" width="500">
    <el-form ref="formRef" :model="form" label-width="auto" :rules="rules" style="max-width: 600px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" />
      </el-form-item>
      <el-form-item label="密码" prop="userpwd">
        <el-input v-model="form.userpwd" type="password" />
      </el-form-item>
       <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio value="1">男</el-radio>
          <el-radio value="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="头像" prop="headurl">
        <el-input v-model="form.headurl" /><el-button type="primary" @click="handleUpload">上传</el-button>
      </el-form-item>
     
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts" name="UserForm">
import { ref, reactive, nextTick, watch } from 'vue';
import type { FormInstance, FormRules } from 'element-plus';

const props = defineProps(['dialogVisible'])
const emit = defineEmits(['update:dialogVisible'])

// 数据表单格式
interface RuleForm {
  username: string;
  name: string;
  age: string;
  sex: string;
  phone: string;
  headurl: string;
  userpwd: string;
}

const formRef = ref<FormInstance>();

// 数据表单数据初始化
const form = reactive<RuleForm>({
  username: '',
  name: '',
  userpwd: '',
  age: '',
  sex: '',
  phone: '',
  headurl: '',
});

// 表单填写的规则
const rules = reactive<FormRules<RuleForm>>({
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
});

// 重置表单
const resetForm = () => {
  Object.assign(form, {
    username: '',
    name: '',
    userpwd: '',
    age: '',
    sex: '',
    phone: '',
    headurl: '',
  });
  nextTick(() => {
    formRef.value?.resetFields();
  });
};

// 取消按钮
const handleCancel = () => {
  emit('update:dialogVisible', false);
};


async function handleUpload()
{
  // 上传头像逻辑
}

// 确认按钮
const handleConfirm = () => {
  emit('update:dialogVisible', false);
};
</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>
