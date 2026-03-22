<template>
  <el-dialog v-model="visible" title="新增用户" width="500">
    <el-form ref="formRef" :model="form" label-width="auto" :rules="rules" style="max-width: 600px">
      <el-form-item label="编号" prop="no">
        <el-input v-model="form.no" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <el-radio-group v-model="form.roleId">
          <el-radio value="1">管理员</el-radio>
          <el-radio value="2">普通用户</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio value="1">男</el-radio>
          <el-radio value="0">女</el-radio>
        </el-radio-group>
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

// 定义props和emits
const props = defineProps<{
  modelValue: boolean;
}>();

const emit = defineEmits<{
  (e: 'update:modelValue', value: boolean): void;
  (e: 'submit', formData: RuleForm): void;
}>();

// 对话框可见性
const visible = ref(props.modelValue);

// 监听props变化
watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
  if (newVal) {
    resetForm();
  }
});

// 监听visible变化，同步到父组件
watch(() => visible.value, (newVal) => {
  emit('update:modelValue', newVal);
});

// 数据表单格式
interface RuleForm {
  name: string;
  no: string;
  age: string;
  sex: string;
  phone: string;
  roleId: string;
  password: string;
}

const formRef = ref<FormInstance>();

// 数据表单数据初始化
const form = reactive<RuleForm>({
  no: '',
  name: '',
  password: '',
  age: '',
  sex: '',
  phone: '',
  roleId: '',
});

// 表单填写的规则
const rules = reactive<FormRules<RuleForm>>({
  no: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 3, max: 5, message: '账号长度必须在3-5位之间', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
  ],
  password: [
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
    no: '',
    name: '',
    password: '',
    age: '',
    sex: '',
    phone: '',
    roleId: '',
  });
  nextTick(() => {
    formRef.value?.resetFields();
  });
};

// 取消按钮
const handleCancel = () => {
  visible.value = false;
};

// 确认按钮
const handleConfirm = () => {
  formRef.value?.validate((valid) => {
    if (valid) {
      emit('submit', { ...form });
      visible.value = false;
    } else {
      alert('请填写完整信息');
    }
  });
};
</script>

<style scoped>
.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>
