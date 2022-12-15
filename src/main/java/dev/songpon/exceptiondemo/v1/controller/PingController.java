package dev.songpon.exceptiondemo.v1.controller;

import dev.songpon.exceptiondemo.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    private PingService service;

    @GetMapping("/v1/ping")
    public String pint() {
        return service.doPing(true);
    }
}
