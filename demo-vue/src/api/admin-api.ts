import { get, post } from '@/utils/request';

/**
 * 管理员API接口
 * 提供管理员相关的增删改查操作
 */
export const adminAPi = {
    /**
     * 添加管理员
     * @param param 管理员信息对象
     * @returns Promise
     */
    add: (param: any) => {
        return post('/admin/add', param);
    },

    /**
     * 删除管理员
     * @param ids 管理员ID
     * @returns Promise
     */
    delete: (ids: any) => {
        return post('/admin/delete?ids=' + ids, {});
    },

    /**
     * 更新管理员信息
     * @param param 管理员信息对象
     * @returns Promise
     */
    update: (param: any) => {
        return post('/admin/update', param);
    },

    /**
     * 分页查询管理员列表
     * @param param 查询条件对象
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @returns Promise
     */
    queryPageList: (param: any, pageNum: any, pageSize: any) => {
        return post('/admin/list?pageNum=' + pageNum + '&pageSize=' + pageSize, param);
    }
};
