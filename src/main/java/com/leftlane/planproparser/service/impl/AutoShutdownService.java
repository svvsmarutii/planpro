package com.leftlane.planproparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AutoShutdownService {

    @Autowired
    private ApplicationContext appContext;

    AtomicBoolean planProFlag = new AtomicBoolean(Boolean.FALSE);
    AtomicBoolean pioFlag = new AtomicBoolean(Boolean.FALSE);
    AtomicBoolean planCustodianFlag = new AtomicBoolean(Boolean.FALSE);

    public void initiateShutdown(int returnCode) {
        if(planProFlag.get() && pioFlag.get() && planCustodianFlag.get()){
            System.out.println("Request received to shut down spring boot application");
            SpringApplication.exit(appContext, () -> returnCode);
        }
    }

    public void markPlanProDone(){
        planProFlag.compareAndSet(Boolean.FALSE, Boolean.TRUE);
        initiateShutdown(0);
    }

    public void markPIODone(){
        pioFlag.compareAndSet(Boolean.FALSE, Boolean.TRUE);
        initiateShutdown(0);
    }

    public void markPlanCustodiansDone(){
        planCustodianFlag.compareAndSet(Boolean.FALSE, Boolean.TRUE);
        initiateShutdown(0);
    }
}
