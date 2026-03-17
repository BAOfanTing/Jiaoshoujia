package com.example.demo.common;

import lombok.Data;

@Data
public class Result {
    private  int code; // 200成功，500失败
    private  String msg;    //成功,失败
    private  Long total; // 总数
    private  Object data;   // 数据

    public static Result success()
    {
        return result(200,"操作成功",0L,null);
    }
    public static Result success(Object data)
    {
        return result(200,"操作成功",0L,data);
    }
    public static Result success(Object data,Long total)
    {
        return result(200,"操作成功",total,data);
    }

    public static Result fail()
    {
        return result(500,"操作失败",0L,null);
    }
    private static Result result(int code,String msg,Long total,Object data)
    {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        res.setTotal(total);
        return res;
    }
}
