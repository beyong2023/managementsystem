package com.sdnu.book.common;

/**
 * 结果集
 *
 * @author Beyong
 * @date 2024/03/20 14:59
 **/
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public String getCode() {
        return code;
    }

    public Result(T data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
