package com.webhook.infrastructure.rest.exception;

import com.webhook.core.exception.PartnerException;
import com.webhook.core.exception.PartnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebhookGlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {PartnerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PartnerException partnerException) {
        log.error(partnerException.getMessage(), partnerException);
        return new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), partnerException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {PartnerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(PartnerNotFoundException notFoundException) {
        log.error(notFoundException.getMessage(), notFoundException);
        return new ErrorDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), notFoundException.getMessage());
    }
}
