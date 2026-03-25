<template>
    <div class="login-container">
        <div class="login-container-content">
            <div class="login-container-content-bg">
                <div class="login-container-content-top">
                    <div>
                        <div style="text-align: center; padding-bottom: 25px;"><img alt="logo" class="login-logo"
                                src="../assets/images/login/logo.png"></div>
                        <span class="login-title">后台管理系统</span>
                    </div>
                </div>
                <div class="login-container-content-main">
                    <el-form size="large" :model="form" :rules="rules" ref="formRef">
                        <el-form-item prop="username">
                            <el-input v-model="form.username" style="width: 368px;height: 40px;font-size:16px;" placeholder="用户名" />
                        </el-form-item>
                        <el-form-item prop="userpwd">
                            <el-input v-model="form.userpwd" style="width:368px;height:40px;font-size:16px;" placeholder="密码" show-password
                                type="password" />
                        </el-form-item>
                        <el-form-item prop="captchaCode">
                            <el-input v-model="form.captchaCode" style="width:250px;height:40px;font-size:16px;" placeholder="验证码" />
                            <br />
                            <img :src="captchaImage" @click="changeCaptcha" />
                        </el-form-item>

                        <el-form-item>


                            <el-button type="primary" @click="handleSubmit" :loading="btnLoading"
                                style="width: 368px; height: 40px; font-size: 16px;">登录</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </div>
    </div>
</template>


<script lang="ts" setup>
import { commonAPi } from '@/api/common-api';
import { adminAPi } from '@/api/admin-api';
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useAdminStore } from '@/stores/admin';

const router = useRouter();
const captchaImage = ref(' ');
const btnLoading = ref(false);


async function getCaptcha() {
    try {
        let caphaResult = await commonAPi.getCaptcha();
        console.log(caphaResult);
        captchaImage.value = caphaResult.data.captchaImage;
        form.captchaID = caphaResult.data.captchaID;
    }
    catch (error) {
        console.error(error);
    }
}

//表单组件
const formRef = ref();

const form = reactive({
    username: undefined,
    userpwd: undefined,
    captchaID: undefined,
    captchaCode: undefined,
})

const rules = reactive({
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 5, message: '用户名长度必须在3-5位之间', trigger: 'blur' },
    ],
    userpwd: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 3, max: 15, message: '密码长度必须在3-15位之间', trigger: 'blur' },
    ],
    captchaCode: [
        { required: true, message: '请输入验证码', trigger: 'blur' },
    ],
});



//登录按钮点击事件处理
async function handleSubmit() {
    try {
        await formRef.value.validate();
        btnLoading.value = true;
        let loginResult = await adminAPi.login(form);

        //存入状态
        useAdminStore().setAdminInfo(loginResult.data);

        router.push('/index');
    }
    catch (error: any) {
        console.error(error);
        // 根据实际API响应结构调整错误信息获取
        const errorMsg = error?.response?.data?.msg || error?.data?.msg || '登录失败';
        ElMessage.error(errorMsg);

        //刷新验证码
        if(errorMsg === '验证码错误')
        {
            getCaptcha();
        }   
    }
    finally {
        btnLoading.value = false;
    }
}

function changeCaptcha() {
    getCaptcha();
}

onMounted(() => {
    getCaptcha();
});

</script>

<style scoped>
.login-container {
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f5f5f5;
}

.login-container-content {
    width: 480px;
    height: 520px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-container-content-bg {
    width: 100%;
    height: 100%;
    padding: 40px;
    box-sizing: border-box;
}

.login-container-content-top {
    margin-bottom: 40px;
}

.login-logo {
    width: 100px;
    height: 100px;
}

.login-title {
    font-size: 20px;
    font-weight: bold;
    color: #303133;
    display: block;
    text-align: center;
    margin-top: 10px;
}

.login-container-content-main {
    text-align: center;
}
</style>