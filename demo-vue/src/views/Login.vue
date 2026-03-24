<template>
<div class="login-container">
<div class="login-container-content">
<div class="login-container-content-bg">
<div class="login-container-content-top">
<div>
<div style="text-align: center; padding-bottom: 25px;"><img alt="logo" class="login-logo" src="../assets/images/login/logo.png"></div>
<span class="login-title">后台管理系统</span>
</div>
</div>
<div class="login-container-content-main">
<el-form size="large">
<el-form-item>
<el-input style="width: 368px;height: 40px;font-size:16px;" placeholder="用户名" />
</el-form-item>
<el-form-item>
<el-input style="width:368px;height:40px;font-size:16px;" placeholder="密码" show-password type="password" />
</el-form-item>
<el-form-item>
<el-input style="width:250px;height:40px;font-size:16px;" placeholder="验证码" />
<br/>
<img :src="captchaImage" @click="changeCaptcha"/>
</el-form-item>

<el-form-item>


<el-button type="primary" style="width: 368px; height: 40px; font-size: 16px;">登录</el-button>
</el-form-item>
</el-form>
</div>
</div>
</div>
</div>
</template>


<script lang="ts" setup>
import { commonAPi } from '@/api/common-api';
import { ref,reactive,onMounted } from 'vue';

const captchaImage = ref(' ');

async function getCaptcha()
{
    try{
        let caphaResult = await commonAPi.getCaptcha();
        console.log(caphaResult);
        captchaImage.value = caphaResult.data.captchaImage;
        form.captchaID = caphaResult.data.captchaID;
    }
    catch (error)
    {
        console.error(error);
    }
}

const form = reactive({
    username: undefined,
    userpwd: undefined,
    captchaID: undefined,
    captchaCode: undefined,
})

function changeCaptcha()
{
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