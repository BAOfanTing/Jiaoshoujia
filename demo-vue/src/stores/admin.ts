import { getLocalstorage, savelocalstorage } from '@/utils/utils';
import { defineStore } from 'pinia'
import { ref, reactive, computed } from 'vue'
import Constants from '@/utils/constants'

//  `defineStore()` 的返回值的命名是自由的
// 但最好含有 store 的名字，且以 `use` 开头，以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useAdminStore = defineStore('admin', ()=>{

   const token = ref(0);
   const admin = reactive({
    id: 0,
    username: '',
    name: '',
    phone: '',
   })

   const getToken = computed(()=>{
    if(token)
    {
        return token;
    }
    return getLocalstorage(Constants.USER_TOKEN);

   })
    function setAdminInfo(data:any)
    {
        admin.id = data.id;
        admin.username = data.username;
        admin.name = data.name;
        admin.phone = data.phone;
        token.value = data.token;
        savelocalstorage(Constants.USER_TOKEN, data.token);
    }

    function logout()
    {
        admin.id = 0 ;
        admin.username = '';
        admin.name = '';
        admin.phone = '';
        token.value = 0;
    }

   return { token, admin ,setAdminInfo,logout}

})