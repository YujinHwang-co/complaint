package com.personal.complaint.server.global;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@ApiResponses({
        @ApiResponse(responseCode = "200", description  = "Success"),
        @ApiResponse(responseCode = "400", description  = "Client errors\t\nBad Request"),
        @ApiResponse(responseCode = "500", description  = "Server errors")
})

public class BaseController {

    @ExceptionHandler(NullPointerException.class) public ResponseBase nullex(Exception e) {
        log.error("error: ", e);
        return new ResponseBase(0, "실패: " + e.toString());
    }

    @ExceptionHandler(RuntimeException.class) public ResponseBase runex(Exception e) {
        log.error("error: ", e);
        return new ResponseBase(0, "실패: " + e.toString());
    }

    @ExceptionHandler(Exception.class) public ResponseBase allex(Exception e) {
        log.error("error: ", e);
        return new ResponseBase(0, "실패:"   +e.toString());
    }

    @ExceptionHandler(BindException.class) public ResponseBase bindex(BindException e) {
        log.error("error: ", e);
        return new ResponseBase(-99, "유효성 검사 실패", ParameterValidUtils.paramsValidResult(e.getBindingResult()));
    }
}
