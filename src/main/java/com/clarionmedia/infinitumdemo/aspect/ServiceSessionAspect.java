package com.clarionmedia.infinitumdemo.aspect;

import com.clarionmedia.infinitum.aop.ProceedingJoinPoint;
import com.clarionmedia.infinitum.aop.annotation.Around;
import com.clarionmedia.infinitum.aop.annotation.Aspect;
import com.clarionmedia.infinitum.context.ContextFactory;
import com.clarionmedia.infinitum.orm.Session;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext;
import com.clarionmedia.infinitum.orm.context.InfinitumOrmContext.SessionType;

@Aspect
public class ServiceSessionAspect {

    private Session mSession;

    public ServiceSessionAspect() {
        mSession = ContextFactory.getInstance().getContext(InfinitumOrmContext.class).getSession(SessionType.SQLITE);
    }

    @Around(within = "com.clarionmedia.infinitumdemo.service")
    public Object manageSession(ProceedingJoinPoint joinPoint) throws Exception {
        boolean close = false;
        if (!mSession.isOpen()) {
            mSession.open();
            close = true;
        }
        Object result = joinPoint.proceed();
        if (close) {
            mSession.close();
        }
        return result;
    }

}
