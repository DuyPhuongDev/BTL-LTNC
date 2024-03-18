package com.ltnc.be.exception.event.log;

import com.lgsi.lgsibe.exception.CommonException;
import com.lgsi.lgsibe.exception.enums.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExceptionEvent {

    protected String errorName;
    protected ErrorCode errorCode;
    protected String errorDetailMsg;
    protected LocalDateTime createdAt;

    private String getStackTraceMessage(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static ExceptionEvent createExceptionEvent(Exception exception) {
        ExceptionEvent exceptionEvent = new ExceptionEvent();
        exceptionEvent.setErrorName(exception.getClass().getSimpleName());
        exceptionEvent.setErrorDetailMsg(exceptionEvent.getStackTraceMessage(exception));
        exceptionEvent.setCreatedAt(LocalDateTime.now());

        return exceptionEvent;
    }

    public static ExceptionEvent createExceptionEvent(CommonException exception) {
        ExceptionEvent exceptionEvent = new ExceptionEvent();
        exceptionEvent.setErrorName(exception.getClass().getSimpleName());
        exceptionEvent.setErrorCode(exception.getErrorCode());
        exceptionEvent.setErrorDetailMsg(exception.getErrorDetailMessage());
        exceptionEvent.setCreatedAt(LocalDateTime.now());

        return exceptionEvent;
    }

    public String getExceptionString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n\n=== === === === === === === === === === === === === === === === === === === === === ===\n\n");
        stringBuilder.append("Exception Title  : ").append(errorName).append("\n");

        // 2. Set Exception
        if (this.errorCode != null) {
            stringBuilder.append("Error Code & Msg : ").append(errorCode.getCode()).append(" / ").append(errorCode.getErrorMessage()).append("\n");
        }

        // 3. Occur Date
        stringBuilder.append("Timestamp        : ").append(createdAt.toString()).append("\n\n");

        // 4. Set Error Detail Msg
        stringBuilder.append(errorDetailMsg);
        stringBuilder.append("\n\n=== === === === === === === === === === === === === === === === === === === === === ===\n\n");

        return stringBuilder.toString();
    }

}
