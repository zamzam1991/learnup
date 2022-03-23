package springboottest.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MyAspectLogger {

    private static final Logger log = LoggerFactory.getLogger(MyAspectLogger.class);

    @Pointcut("@annotation(springboottest.annotations.LogMethod)")
    public void callLog() {}

    @Around("callLog()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        String str = null;
        if (joinPoint.getArgs().length > 0)
            str = Arrays.toString(joinPoint.getArgs());
        log.info("methodName: {}, args: {}, result: {}", joinPoint.getSignature().getName(), str, result);
        return result;
    }

}
