package com.personal.complaint.server.api.controller;

import com.personal.complaint.server.global.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
