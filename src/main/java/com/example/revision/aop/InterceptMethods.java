package com.example.revision.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InterceptMethods {
    private final static Logger LOGGER = LoggerFactory.getLogger(InterceptMethods.class);

    @Before(value = "com.example.revision.aop.Pointcuts.subscribeEvents()")
    public void interceptSubscribe(JoinPoint point) {
        LOGGER.info("interceptSubscribe {}", point.getTarget());
    }

    @AfterThrowing(value = "com.example.revision.aop.Pointcuts.repositoryErrorEvents()", throwing = "e")
    public void repositoriesErrorsInterceptor(JoinPoint point, DataAccessException e) {
        LOGGER.error("exception {} in repositories {}", e.getMessage(), point.getTarget());
    }
}
