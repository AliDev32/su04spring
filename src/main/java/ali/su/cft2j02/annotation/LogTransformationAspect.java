package ali.su.cft2j02.annotation;

import ali.su.cft2j02.config.LogConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class LogTransformationAspect {
    private final LogConfig logConfig;

    @Around("@annotation(LogTransformation)")
    public Object logTransformation(ProceedingJoinPoint joinPoint) throws Throwable {
        final var dateFormatter = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss z]: ");
        final var currentDateTime = dateFormatter.format(new Date());

        final var signature = (MethodSignature) joinPoint.getSignature();
        final var method = signature.getMethod();
        final var logTransformationAnnotation = method.getAnnotation(LogTransformation.class);
        final var fileName = logTransformationAnnotation.logFileName();

        final var inputData = Arrays.toString(joinPoint.getArgs());

        final var proceedData = joinPoint.proceed();

        logSave(fileName, currentDateTime, joinPoint.getTarget().getClass().getSimpleName(),
                inputData, proceedData);

        return proceedData;
    }

    private void logSave(String fileName, String dateTime, String className, Object inputData, Object outData) {
        final var logLocation = logConfig.getLocation();
        final var fullFileName = logLocation.concat("/").concat(fileName);

        final var str = String.format("%s%s\n input: %s\noutput: %s\n",
                                      dateTime,
                                      className,
                                      inputData,
                                      outData
                                     );
        try {
            if (!Files.exists(Path.of(logLocation))) {
                Files.createDirectories(Path.of(logLocation));
            }
            Files.write(Path.of(fullFileName), str.getBytes(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ошибка записи в лог: " + fileName + " : " + e);
        }
    }

    public LogTransformationAspect(LogConfig logConfig) {
        this.logConfig = logConfig;
    }
}
