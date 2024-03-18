package com.ltnc.be.exception.event;

import com.lgsi.lgsibe.exception.event.log.ExceptionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventListener {

    @Async
    @EventListener
    public void onExceptionEvent(ExceptionEvent exceptionEvent) {
        log.error(exceptionEvent.getExceptionString());
    }

}
