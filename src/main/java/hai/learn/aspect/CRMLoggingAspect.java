package hai.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private final Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
@Pointcut("execution(* hai.learn.controller.*.*(..))")
    private void forControllerPackage(){

}

    // do same for service an dao
    @Pointcut("execution(* hai.learn.service.*.*(..)))")
    private void forServicePackage(){}

    @Pointcut("execution(* hai.learn.dao.*.*(..)))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){
    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("========>>>> in Before: calling method: " + method);

        // display the arguments to method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for(Object tempArgs: args){
            myLogger.info("============> argument: " + tempArgs);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint joinPoint, Object theResult){

    // display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("========>>>> in @AfterReturning: calling method: " + method);

        // display the data returned
        myLogger.info("==============>> result: "+theResult);
    }
}
