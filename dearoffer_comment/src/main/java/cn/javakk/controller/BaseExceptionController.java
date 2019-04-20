//package cn.javakk.controller;
//
//import cn.javakk.pojo.Result;
//import cn.javakk.pojo.StatusCode;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 全局异常处理类
// * @Author: javakk
// * @Date: 2019/4/13 14:48
// */
//
//@ControllerAdvice
//public class BaseExceptionController {
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result handle() {
//        // 发生了异常需处理
//        // Todo: 异常分类处理 @javaKK
//        return new Result(false, StatusCode.ERROR, "Error");
//    }
//}
