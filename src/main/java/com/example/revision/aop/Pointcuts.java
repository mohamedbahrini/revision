package com.example.revision.aop;

import org.aspectj.lang.annotation.Pointcut;


public class Pointcuts {
    @Pointcut(value = "execution(* com.example.revision.controller.UserController.registerUser(..))")
    public void subscribeEvents() {
    }

    @Pointcut(value = "execution(* com.example.revision.controller.AuthenticationController.authenticateUser(..))")
    public void loginEvents() {
    }

    @Pointcut(value = "execution(* *..*Repository.*(..))")
    public void repositoryErrorEvents() {
    }

    @Pointcut(value = "execution(* *..controller.UserController.*(..))")
    public void userControllerEvents() {
    }
}
