package com.ltnc.be.rest.response;

import lombok.*;

import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class BaseResponse<T> {
  private T data;
  private String message;
  private String stackTrace;
  private String exceptionCode;

  protected static final String SUCCEED_REQUEST_MESSAGE = "Success";

  public static <T> BaseResponse<T> of(T data) {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(data)
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build();
  }

  public static <T> BaseResponse<T> ok() {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(null)
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build();
  }

  public static <T> BaseResponse<T> error(
      String exceptionCode, String exceptionMessage, String stackTrace) {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(null)
            .message(exceptionMessage)
            .exceptionCode(exceptionCode)
            .stackTrace(stackTrace)
            .build();
  }

  public static <T> BaseResponse<T> empty() {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(Collections.emptyList())
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build();
  }
}
