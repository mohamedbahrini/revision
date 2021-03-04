package com.example.revision.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
class ContextWrapper {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Autowired
    public static void setContext(ApplicationContext context) {
        ContextWrapper.context = context;
    }
}
