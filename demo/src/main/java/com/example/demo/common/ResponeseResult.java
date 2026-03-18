package com.example.demo.common;

import lombok.Data;

@Data
public class ResponeseResult {
    private  int code; // 200成功，500失败
    private  String msg;    //成功,失败
    private  Long total; // 总数
    private  Object data;   // 数据

    public static ResponeseResult success()
    {
        return result(200,"操作成功",0L,null);
    }
    public static ResponeseResult success(Object data)
    {
        return result(200,"操作成功",0L,data);
    }
    public static ResponeseResult success(Object data, Long total)
    {
        return result(200,"操作成功",total,data);
    }

    public static ResponeseResult fail()
    {
        return result(500,"操作失败",0L,null);
    }
    private static ResponeseResult result(int code, String msg, Long total, Object data)
    {
        ResponeseResult res = new ResponeseResult();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        res.setTotal(total);
        return res;
    }
}
