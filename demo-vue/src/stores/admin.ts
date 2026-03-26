import { getLocalstorage, savelocalstorage } from '@/utils/utils';
import { defineStore } from 'pinia'
import { ref, reactive, computed, onMounted } from 'vue'
import Constants from '@/utils/constants'

//  `defineStore()` 的返回值的命名是自由的
// 但最好含有 store 的名字，且以 `use` 开头，以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useAdminStore = defineStore('admin', ()=>{

   const token = ref('');
   const admin = reactive({
    id: 0,
    username: '',
    name: '',
    phone: '',
   })

   function getToken()
   {
    if(token.value)
    {
        return token.value;
    }
    return getLocalstorage(Constants.USER_TOKEN);

   }

   function getAdminInfo() {
    const adminStr = getLocalstorage('adminInfo');
    if (adminStr) {
        try {
            const adminData = JSON.parse(adminStr);
            admin.id = adminData.id || 0;
            admin.username = adminData.username || '';
            admin.name = adminData.name || '';
            admin.phone = adminData.phone || '';
        } catch (e) {
            console.error('解析admin信息失败:', e);
        }
    }
   }

    function setAdminInfo(data:any)
    {
        admin.id = data.id;
        admin.username = data.username;
        admin.name = data.name;
        admin.phone = data.phone;
        token.value = data.token;
        savelocalstorage(Constants.USER_TOKEN, data.token);
        savelocalstorage('adminInfo', JSON.stringify(admin));
    }

    function logout()
    {
        admin.id = 0 ;
        admin.username = '';
        admin.name = '';
        admin.phone = '';
        token.value = '';
        savelocalstorage(Constants.USER_TOKEN, '');
        savelocalstorage('adminInfo', '');
    }

    // 初始化时从本地存储加载admin信息
    getAdminInfo();

   return { token, admin ,setAdminInfo,logout,getToken, getAdminInfo}

})