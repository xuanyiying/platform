package com.platform.management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wangying
 */
@Aspect
@Component
public class SystemLogAspect extends  AbstractAspect{
    /**
     * update and delete
     */
    @Pointcut("(execution(* com.platform.management.controller.*Controller.delete*(..))" +
            "|| execution(* com.platform.management.controller.*Controller.update*(..)))"+
            "|| execution(* com.platform.management.controller.*Controller.add*(..)))")
    public void doBefore() {
    }

    @Override
    @Before("doBefore()")
	public void doBefore(JoinPoint pjp) {
		super.doBefore(pjp);
	}
}
