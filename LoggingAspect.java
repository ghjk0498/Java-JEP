import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 모든 클래스의 모든 메소드에 적용
    @Pointcut("within(com.yourcompany.yourproject..*)")
    public void allMethodsPointcut() {}

    // 메소드 실행 전후에 디버그 및 트레이스 로그 출력
    @Around("allMethodsPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        
        // 메소드 시작 전에 디버그 로그 출력
        if (logger.isDebugEnabled()) {
            logger.debug("Start: {}", joinPoint.getSignature().getName());
        }

        // 메소드 실행 전에 트레이스 로그 출력
        if (logger.isTraceEnabled()) {
            logger.trace("Input Arguments: {}", joinPoint.getArgs());
        }

        try {
            Object result = joinPoint.proceed(); // 메소드 실행

            // 메소드 실행 후에 디버그 로그 출력
            if (logger.isDebugEnabled()) {
                logger.debug("End: {}", joinPoint.getSignature().getName());
            }

            // 메소드 실행 후에 트레이스 로그 출력
            if (logger.isTraceEnabled()) {
                logger.trace("Return: {}", result);
            }

            return result;
        } catch (IllegalArgumentException e) {
            // 메소드 실행 중 예외 발생 시 로그 출력
            logger.error("Illegal argument: {} in {}", joinPoint.getArgs(), joinPoint.getSignature().getName());
            throw e; // 예외를 다시 던짐
        }
    }
}
