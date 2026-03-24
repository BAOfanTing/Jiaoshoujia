import { get, post } from '@/utils/request';

/**
 * 管理员API接口
 * 提供管理员相关的增删改查操作
 */
export const commonAPi = {
    /**
     * 获取验证码
     * @returns Promise
     */
    getCaptcha: () => {
        return get('/common/getCaptcha', {});
    }

}