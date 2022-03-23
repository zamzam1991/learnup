package springboottest.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeCounter {

    private static final Logger log = LoggerFactory.getLogger(ExecutionTimeCounter.class);

    @Pointcut("execution(* springboottest.events.*.*(..))")
    public void callTimer() {}

    @Around("callTimer()")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("{}: runtime - {} millis", joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
