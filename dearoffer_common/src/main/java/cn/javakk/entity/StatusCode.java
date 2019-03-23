package cn.javakk.entity;

/**
 * 返回状态码
 * @Author: javakk
 * @Date: 2019/3/23 16:34
 */
public class StatusCode {
    /**
     * 成功
     */
    public static final int OK = 1000;

    /**
     * 失败
     */
    public static final int ERROR = 1001;

    /**
     * 密码错误
     */
    public static final int LOGINERROR = 1002;

    /**
     * 权限不足
     */
    public static final int ACCESSERROR = 1003;

    /**
     * 远程调用失败
     */
    public static final int REMOTEERROR = 1004;

    /**
     * 重复操作
     */
    public static final int REPETERROR = 1005;
}
