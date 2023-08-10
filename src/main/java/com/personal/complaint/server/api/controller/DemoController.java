package com.personal.complaint.server.api.controller;

import com.personal.complaint.server.global.BaseController;
import com.personal.complaint.server.global.ResponseBase;
import com.personal.complaint.server.model.DemoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class DemoController extends BaseController {

    @Operation(summary = "swagger test", description = "swagger test")
    @GetMapping("/demo")
    public String helloWorld() {
        return "demo controller";
    }

    @GetMapping("/example1")
    public String example1(@Parameter String test) {
        log.info("test");
        return test;
    }

    @GetMapping("/example2")
    public DemoVo example2(@ParameterObject DemoVo demoVo) {
        return demoVo;
    }

    /**
     * parameter validation 체크 example
     * @param demoVo
     * @return
     */
    @GetMapping("/example3_parameter_valid")
    public ResponseBase example3_parameter_valid(@ParameterObject @Validated DemoVo demoVo) {
        ResponseBase rb = new ResponseBase();
        rb.setStatusCodeMsg(1, "성공", demoVo);
        return rb;
    }
}
