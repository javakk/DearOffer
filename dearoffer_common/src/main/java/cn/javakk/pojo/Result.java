package cn.javakk.pojo;

/**
 * @Author: javakk
 * @Date: 2019/3/23 16:26
 */
public class Result {
    /**
     * 是否成功
     */
    private boolean isSuccess;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public Result(boolean isSuccess, Integer code, String message, Object data) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
        this.isSuccess = true;
        this.code = StatusCode.OK;
        this.message = "操作成功";
    };

    public Result(Object data) {
        this.isSuccess = true;
        this.code = StatusCode.OK;
        this.message = "操作成功";
        this.data = data;
    };
}
